package vn.edu.iuh.fit.lab_week01.services.impl;

import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.respositories.AccountRespository;
import vn.edu.iuh.fit.lab_week01.services.AccountService;

import java.sql.SQLException;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AccountRespository accountRespository;

    public AccountServiceImpl() throws Exception {
        accountRespository = new AccountRespository();
    }
    @Override
    public int login(String username, String password) throws SQLException {
        return accountRespository.login(username, password);
    }

    @Override
    public boolean register(String username, String password, String email, String phone) {
        return false;
    }

    @Override
    public List<Account> getAllAccount() throws SQLException {
        return accountRespository.getALL();
    }

    @Override
    public Account getAccountById(String id) throws SQLException {
        return accountRespository.getOne(id);
    }
}
