package service;
import java.util.List;
import domain.Account;

public interface BankService {
    String openAccount(String name, String email, String accountType);
    List<Account> listAccounts();
    void deposit(String accountNumber, Double amount, String note);
    void withdraw(String accountNumber, Double amount, String note);
}