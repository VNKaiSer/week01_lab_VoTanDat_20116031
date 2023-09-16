package vn.edu.iuh.fit.lab_week01.services;

import vn.edu.iuh.fit.lab_week01.models.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    int login(String username, String password) throws SQLException;
     boolean register(String username, String password, String email, String phone);

     List<Account> getAllAccount() throws SQLException;

     Account getAccountById(String id) throws SQLException;

     boolean editAccount(Account object);


    boolean createAccount(Account account);

    void deleteAccount(String accountId);

}
