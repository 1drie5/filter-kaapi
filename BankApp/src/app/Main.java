package app;
import domain.Account;
import domain.Customer;
import domain.Transaction;
import exceptions.InsufficientFundsException;
import exceptions.AccountNotFoundException;
import exceptions.ValidationException;
import service.BankService;
import service.impl.BankServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    private static final String SEPARATOR = "===============================================================";
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
            String type;
            while (true) {
                System.out.println("Account Type (Enter 1 for SAVINGS, 2 for CURRENT): ");
                String choice = scanner.nextLine().trim();

                if (choice.equals("1")) {
                    type = "SAVINGS";
                    break;
                } else if (choice.equals("2")) {
                    type = "CURRENT";
                    break;
                } else {
                    System.out.println(RED + "Invalid choice. Please enter exactly 1 or 2." + RESET);
                }
            }
            System.out.println("Initial deposit (optional, blank for 0): ");
            String amountStr = scanner.nextLine().trim();
            if (amountStr.isBlank()) amountStr = "0";

            Double initial = Double.parseDouble(amountStr);
            String accountNumber = bankService.openAccount(name, email, type);

            if (initial > 0) {
                bankService.deposit(accountNumber, initial, "Initial Deposit");
            }
            System.out.println(GREEN + "Account opened: " + accountNumber + RESET);

        } catch (NumberFormatException e) {
            System.out.println(RED + "Error: Initial deposit must be a valid number." + RESET);
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(RED + "Error: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
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
            System.out.println(GREEN + "Deposited successfully." + RESET);

        } catch (NumberFormatException e) {
            System.out.println(RED + "Error: Deposit amount must be a valid number." + RESET);
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(RED + "Error: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
        }
    }

    private static void withdraw(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Account number: ");
            String accountNumber = scanner.nextLine().trim();
            System.out.println("Amount: ");
            Double amount = Double.valueOf(scanner.nextLine().trim());

            bankService.withdraw(accountNumber, amount, "Withdrawal");
            System.out.println(GREEN + "Withdrawn successfully." + RESET);
            Account updatedAccount = bankService.getAccountByNumber(accountNumber);
            if (updatedAccount.getBalance() < 50.00) {
                System.out.println(YELLOW + "Alert! Your account balance is low $" + updatedAccount.getBalance() + "." + RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(RED + "Error: Withdrawal amount must be a valid number." + RESET);
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(RED + "Error: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
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
            System.out.printf(GREEN + "Successfully transferred %.2f from %s to %s." + RESET + "%n", amount, from, to);

        } catch (NumberFormatException e) {
            System.out.println(RED + "Error: Transfer amount must be a valid number." + RESET);
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(RED + "Error: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
        }
    }

    private static void statement(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Account number: ");
            String accountNumber = scanner.nextLine().trim();
            Account account = bankService.getAccountByNumber(accountNumber);
            Customer customer = bankService.getCustomerById(account.getCustomerId());
            List<Transaction> transactions = bankService.getStatement(accountNumber);
            System.out.println();
            PrintWriter consoleWriter = new PrintWriter(System.out, true);
            printStatement(consoleWriter, account, customer, transactions);
            System.out.println();
            System.out.println("Do you want to save this statement as a text file? (y/yes): ");
            String saveChoice = scanner.nextLine().trim().toLowerCase();
            if (saveChoice.equals("y") || saveChoice.equals("yes")) {
                String safeAccountNumber = account.getAccountNumber().replaceAll("[^a-zA-Z0-9]", "_");
                String filename = safeAccountNumber + "_statement.txt";
                try (PrintWriter fileWriter = new PrintWriter(filename)) {
                    printStatement(fileWriter, account, customer, transactions);
                    System.out.println(GREEN + "Success! Statement saved to " + filename + RESET);
                } catch (IOException ex) {
                    System.out.println(RED + "Error writing to file: " + ex.getMessage() + RESET);
                }
            }
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(RED + "Error: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
        }
    }

    private static void printStatement(PrintWriter writer, Account account, Customer customer, List<Transaction> transactions) {
        writer.println(SEPARATOR);
        writer.println("                        ACCOUNT STATEMENT                      ");
        writer.println(SEPARATOR);
        writer.printf("Name         : %s%n", (customer != null ? customer.getName() : "Unknown"));
        writer.printf("Email        : %s%n", (customer != null ? customer.getEmail() : "Unknown"));
        writer.printf("Account No   : %s%n", account.getAccountNumber());
        writer.printf("Account Type : %s%n", account.getAccountType());
        writer.printf("Current Bal  : $%.2f%n", account.getBalance());
        writer.println("---------------------------------------------------------------");

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (Transaction t : transactions) {
            writer.printf("%s | %-12s | $%8.2f | %s%n",
                    t.getTimestamp().format(fmt),
                    t.getType(),
                    t.getAmount(),
                    t.getNote());
        }
        writer.println(SEPARATOR);
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
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
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
            System.out.println(RED + "Error: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
        }
    }

    private static void closeAccount(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Account number to close: ");
            String accountNumber = scanner.nextLine().trim();
            Account account = bankService.getAccountByNumber(accountNumber);
            double payout = account.getBalance();
            bankService.closeAccount(accountNumber);
            System.out.println(GREEN + "Account " + accountNumber + " has been successfully closed." + RESET);
            if (payout > 0) {
                System.out.printf(GREEN + "A final payout of $%.2f has been processed." + RESET + "%n", payout);
            }
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(RED + "Error: " + e.getMessage() + RESET);
        } catch (Exception e) {
            System.out.println(RED + "An unexpected error occurred: " + e.getMessage() + RESET);
        }
    }
}