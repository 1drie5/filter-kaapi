package service;
import java.util.List;
import domain.Account;
import domain.Customer;
import domain.Transaction;

public interface BankService {
    String openAccount(String name, String email, String accountType);
    List<Account> listAccounts();
    void deposit(String accountNumber, Double amount, String note);
    void withdraw(String accountNumber, Double amount, String note);
    void transfer(String from, String to, Double amount, String transfer);
    List<Transaction> getStatement(String account);
    List<Account> searchAccountsByCustomerName(String q);
    Customer getCustomerById(String customerId);
}