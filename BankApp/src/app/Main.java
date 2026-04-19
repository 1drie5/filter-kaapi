package app;
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
            System.out.println("\n---------------------------------------------------");
            System.out.println("""
                        1) Open Account
                        2) Deposit
                        3) Withdraw
                        4) Transfer
                        5) Account Statement
                        6) List Accounts
                        7) Search Accounts by Customer Name
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

            bankService.deposit(accountNumber, amount, "Deposit");
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

            bankService.transfer(from, to, amount, "Transfer");
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
            String account = scanner.nextLine().trim();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); // The formatter

            bankService.getStatement(account).forEach(t -> {
                System.out.printf("%s | %s | %.2f | %s%n",
                        t.getTimestamp().format(fmt),
                        t.getType(),
                        t.getAmount(),
                        t.getNote());
            });

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
                System.out.printf("%s | %-15s | %s | $%.2f%n",
                        a.getAccountNumber(),
                        customerName,
                        a.getAccountType(),
                        a.getBalance());
            });
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void searchAccounts(Scanner scanner, BankService bankService) {
        try {
            System.out.println("Customer name contains: ");
            String q = scanner.nextLine().trim();

            bankService.searchAccountsByCustomerName(q).forEach(a ->
                    System.out.printf("%s | %s | $%.2f%n", a.getAccountNumber(), a.getAccountType(), a.getBalance()
            ));
        } catch (ValidationException | AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}