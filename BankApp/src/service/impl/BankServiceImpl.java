package service.impl;

import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundsException;
import exceptions.ValidationException;
import repository.AccountRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import service.BankService;
import util.Validation;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository = new AccountRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

    private final Validation<String> validateName = name -> {
        if (name == null || name.isBlank()) throw new ValidationException("Name is required");
    };

    private final Validation<String> validateEmail = email -> {
        if (email == null || !email.contains("@")) throw new ValidationException("Email is required");
    };

    private final Validation<String> validateType = type -> {
        if (type == null || !(type.equalsIgnoreCase("SAVINGS") || type.equalsIgnoreCase("CURRENT")))
            throw new ValidationException("Type must be SAVINGS or CURRENT");
    };

    private final Validation<Double> validateAmountPositive = amount -> {
        // Change < 0 to <= 0
        if (amount == null || amount <= 0)
            throw new ValidationException("Amount must be greater than zero.");
    };

    @Override
    public String openAccount(String name, String email, String accountType) {
        validateName.validate(name);
        validateEmail.validate(email);
        validateType.validate(accountType);

        String customerId = UUID.randomUUID().toString();
        // Create customer
        Customer c = new Customer(email, customerId, name);
        customerRepository.save(c);
        // change later --> 10 + 1 = AC11
        // String accountNumber = UUID.randomUUID().toString();
        String accountNumber = getAccountNumber();
        Account account = new Account(accountNumber,accountType, (double) 0,customerId);
        accountRepository.save(account);
        return accountNumber;
    }

    @Override
    public List<Account> listAccounts() {
        return accountRepository.findAll().stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    @Override
    public void deposit(String accountNumber, Double amount, String note) {
        validateAmountPositive.validate(amount);
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        if (!account.isActive()) {
            throw new ValidationException("Transaction failed: This account is closed.");
        }
        account.setBalance(account.getBalance() + amount);
        Transaction transaction = new Transaction(account.getAccountNumber(),
                amount, UUID.randomUUID().toString(), note, LocalDateTime.now(), Type.DEPOSIT);
        transactionRepository.add(transaction);
    }

    @Override
    public void withdraw(String accountNumber, Double amount, String note) {
        validateAmountPositive.validate(amount);
        Account account = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        if (!account.isActive()) {
            throw new ValidationException("Transaction failed: This account is closed.");
        }

        double limit = account.getAccountType().equals("SAVINGS") ? 100.0 : -500.0;
        if ((account.getBalance() - amount) < limit) {
            throw new InsufficientFundsException(String.format("Transaction failed: %s limit is $%.2f", account.getAccountType(), limit));
        }

        account.setBalance(account.getBalance() - amount);
        Transaction transaction = new Transaction(account.getAccountNumber(),
                amount, UUID.randomUUID().toString(), note, LocalDateTime.now(), Type.WITHDRAW);
        transactionRepository.add(transaction);
    }

    @Override
    public void transfer(String fromAcc, String toAcc, Double amount, String note) {
        validateAmountPositive.validate(amount);
        if(fromAcc.equals(toAcc))
            throw new ValidationException("Cannot transfer to your own account");
        Account from = accountRepository.findByNumber(fromAcc)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + fromAcc));

        // Block if sender is closed
        if (!from.isActive()) {
            throw new ValidationException("Transaction failed: Sender account is closed.");
        }

        Account to = accountRepository.findByNumber(toAcc)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + toAcc));

        // Block if receiver is closed
        if (!to.isActive()) {
            throw new ValidationException("Transaction failed: Receiver account is closed.");
        }

        double limit = from.getAccountType().equals("SAVINGS") ? 100.0 : -500.0;

        if ((from.getBalance() - amount) < limit) {
            throw new InsufficientFundsException(String.format("Transfer failed: Sender's %s limit is $%.2f", from.getAccountType(), limit));
        }
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        transactionRepository.add(new Transaction(from.getAccountNumber(),
                amount, UUID.randomUUID().toString(), note, LocalDateTime.now(), Type.TRANSFER_OUT));
        transactionRepository.add(new Transaction(to.getAccountNumber(),
                amount, UUID.randomUUID().toString(), note, LocalDateTime.now(), Type.TRANSFER_IN));
    }

    @Override
    public List<Transaction> getStatement(String account) {
        accountRepository.findByNumber(account)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + account));
        return transactionRepository.findByAccount(account).stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountsByCustomerName(String q) {
        String query = (q == null) ? "" : q.toLowerCase();
        /*

        List<Account> result = new ArrayList<>();
        for (Customer c : customerRepository.findAll()){
            if (c.getName().toLowerCase().contains(query))
                result.addAll(accountRepository.findByCustomerId(c.getId()));
        }
        result.sort(Comparator.comparing(Account::getAccountNumber));
        return result;

        */
        return customerRepository.findAll().stream()
                .filter(c -> c.getName().toLowerCase().contains(query))
                .flatMap(c -> accountRepository.findByCustomerId(c.getId()).stream())
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size() + 1;
        return String.format("AC%06d", size);
    }

    @Override
    public void closeAccount(String accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        if (!account.isActive()) {
            throw new ValidationException("Account is already closed.");
        }
        if (account.getBalance() < 0) {
            throw new ValidationException(String.format("Cannot close account. Please pay off your overdraft of $%.2f first.", Math.abs(account.getBalance())));
        }
        if (account.getBalance() > 0) {
            double payout = account.getBalance();
            account.setBalance(0.0);
            transactionRepository.add(new Transaction(account.getAccountNumber(),
                    payout, UUID.randomUUID().toString(), "Account Closure - Final Payout", LocalDateTime.now(), Type.WITHDRAW));
        }
        account.setActive(false);
    }
}
