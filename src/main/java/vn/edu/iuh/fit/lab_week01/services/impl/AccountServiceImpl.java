package vn.edu.iuh.fit.lab_week01.services.impl;

import vn.edu.iuh.fit.lab_week01.respositories.AccountRespository;
import vn.edu.iuh.fit.lab_week01.services.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountRespository accountRespository;

    public AccountServiceImpl() throws Exception {
        accountRespository = new AccountRespository();
    }
    @Override
    public boolean login(String username, String password) {
        return ;
    }

    @Override
    public boolean register(String username, String password, String email, String phone) {
        return false;
    }
}
