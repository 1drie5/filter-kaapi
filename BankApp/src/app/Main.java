package app;
import domain.Account;
import domain.Customer;
import exceptions.InsufficientFundsException;
import exceptions.AccountNotFoundException;
import exceptions.ValidationException;
import service.BankService;
import service.impl.BankServiceImpl;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankServiceImpl();
        boolean running = true;
        System.out.println("Welcome to Console Bank");
        while (running) {
            System.out.println("\n---------------------------------------------------------------");
            System.out.println("""
                        1) Open Account
                        2) Deposit
                        3) Withdraw
                        4) Transfer
                        5) Account Statement
                        6) List Accounts
                        7) Search Accounts by Customer Name
                        8) Close Account
                        0) Exit
                    """);
            System.out.print("CHOOSE: ");
            String choice = scanner.nextLine().trim();
            System.out.println("CHOICE: " + choice);

            switch (choice) {
                case "1" -> openAccount(scanner, bankService);
                case "2" -> deposit(scanner, bankService);
                case "3" -> withdraw(scanner, bankService);
                case "4" -> transfer(scanner, bankService);
                case "5" -> statement(scanner, bankService);
                case "6" -> listAccounts(scanner, bankService);
                case "7" -> searchAccounts(scanner, bankService);
                case "8" -> closeAccount(scanner, bankService);
                case "0" -> running = false;
            }
        }
        System.out.println("\nThank you for using Console Bank. Goodbye!");
        scanner.close();
    }

    private static void openAccount(Scanner scanner,  BankService bankService) {
        try {
            System.out.println("Customer name: ");
            String name = scanner.nextLine().trim();
            System.out.println("Customer email: ");
            String email = scanner.nextLine().trim();
            System.out.println("Account Type (SAVINGS/CURRENT): ");
            String type = scanner.nextLine().trim();
            System.out.println("Initial deposit (optional, blank for 0): ");
            String amountStr = scanner.nextLine().trim();
            if (amountStr.isBlank()) amountStr = "0";

            Double initial = Double.parseDouble(amountStr);
            String accountNumber = bankService.openAccount(name, email, type);

            if (initial > 0) {
                bankService.deposit(accountNumber, initial, "Initial Deposit");
            }
            System.out.println("Account opened: " + accountNumber);

        } catch (NumberFormatException e) {
            System.out.println("Error: Initial deposit must be a valid number.");
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void deposit(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Account number: ");
            String accountNumber = scanner.nextLine().trim();
            System.out.println("Amount: ");
            Double amount = Double.valueOf(scanner.nextLine().trim());

            String note;
            while (true) {
                System.out.println("Add a note (optional): ");
                note = scanner.nextLine().trim();

                if (!note.isBlank()) {
                    break;
                }

                System.out.println("You didn't add a note. Continue with default note 'Deposit'? (yes/no)");
                String confirm = scanner.nextLine().trim().toLowerCase();

                if (confirm.equals("yes") || confirm.equals("y")) {
                    note = "Deposit";
                    break;
                }
                System.out.println("Okay, please enter your custom note.");
            }

            bankService.deposit(accountNumber, amount, note);
            System.out.println("Deposited successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Deposit amount must be a valid number.");
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void withdraw(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Account number: ");
            String accountNumber = scanner.nextLine().trim();
            System.out.println("Amount: ");
            Double amount = Double.valueOf(scanner.nextLine().trim());

            bankService.withdraw(accountNumber, amount, "Withdrawal");
            System.out.println("Withdrawn successfully.");
            Account updatedAccount = bankService.getAccountByNumber(accountNumber);
            if (updatedAccount.getBalance() < 50.00) {
                System.out.println("Alert! Your account balance is low $" + updatedAccount.getBalance() + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Withdrawal amount must be a valid number.");
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void transfer(Scanner scanner, BankService bankService ) {
        try {
            System.out.println("From Account: ");
            String from = scanner.nextLine().trim();
            System.out.println("To Account: ");
            String to = scanner.nextLine().trim();
            System.out.println("Amount: ");
            Double amount = Double.valueOf(scanner.nextLine().trim());
            String note;
            while (true) {
                System.out.println("Add a note (optional): ");
                note = scanner.nextLine().trim();
                if (!note.isBlank()) {
                    break;
                }
                System.out.println("You didn't add a note. Continue with default note 'Transfer'? (yes/no)");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (confirm.equals("yes") || confirm.equals("y")) {
                    note = "Transfer";
                    break;
                }
                System.out.println("Okay, please enter your custom note.");
            }
            bankService.transfer(from, to, amount, note);
            System.out.printf("Successfully transferred %.2f from %s to %s.%n", amount, from, to);

        } catch (NumberFormatException e) {
            System.out.println("Error: Transfer amount must be a valid number.");
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void statement(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Account number: ");
            String accountNumber = scanner.nextLine().trim();

            // 1. Fetch Account and Customer details
            Account account = bankService.getAccountByNumber(accountNumber);
            Customer customer = bankService.getCustomerById(account.getCustomerId());

            // 2. Print the personal info header
            System.out.println("\n===============================================================");
            System.out.println("                        ACCOUNT STATEMENT                      ");
            System.out.println("===============================================================");
            System.out.println("Name         : " + (customer != null ? customer.getName() : "Unknown"));
            System.out.println("Email        : " + (customer != null ? customer.getEmail() : "Unknown"));
            System.out.println("Account No   : " + account.getAccountNumber());
            System.out.println("Account Type : " + account.getAccountType());
            System.out.printf("Current Bal  : $%.2f%n", account.getBalance());
            System.out.println("---------------------------------------------------------------");

            // 3. Print the transactions
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            bankService.getStatement(accountNumber).forEach(t -> {
                // Formatted with %-10s for type and %7.2f for amount to keep columns aligned
                System.out.printf("%s | %-12s | $%8.2f | %s%n",
                        t.getTimestamp().format(fmt),
                        t.getType(),
                        t.getAmount(),
                        t.getNote());
            });
            System.out.println("===============================================================\n");

        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void listAccounts(Scanner scanner, BankService bankService) {
        try {
            bankService.listAccounts().forEach(a -> {
                Customer customer = bankService.getCustomerById(a.getCustomerId());
                String customerName = (customer != null) ? customer.getName() : "Unknown";
                String status = a.isActive() ? "ACTIVE" : "CLOSED";
                System.out.printf("%s | %-15s | %-8s | %s | $%.2f%n",
                        a.getAccountNumber(),
                        customerName,
                        status,
                        a.getAccountType(),
                        a.getBalance());
            });
            System.out.println("---------------------------------------------------------------");
            double totalReserves = bankService.listAccounts().stream()
                    .filter(Account::isActive)
                    .mapToDouble(Account::getBalance)
                    .sum();
            System.out.printf("TOTAL BANK RESERVES: $%.2f%n", totalReserves);
            System.out.println("===============================================================");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void searchAccounts(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Customer name contains: ");
            String q = scanner.nextLine().trim();

            bankService.searchAccountsByCustomerName(q).forEach(a -> {
                Customer customer = bankService.getCustomerById(a.getCustomerId());
                String customerName = (customer != null) ? customer.getName() : "Unknown";
                String status = a.isActive() ? "ACTIVE" : "CLOSED";

                System.out.printf("%s | %-15s | %-8s | %s | $%.2f%n",
                        a.getAccountNumber(),
                        customerName,
                        status,
                        a.getAccountType(),
                        a.getBalance());
            });
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void closeAccount(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Account number to close: ");
            String accountNumber = scanner.nextLine().trim();

            bankService.closeAccount(accountNumber);
            System.out.println("Account " + accountNumber + " has been successfully closed.");

        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}