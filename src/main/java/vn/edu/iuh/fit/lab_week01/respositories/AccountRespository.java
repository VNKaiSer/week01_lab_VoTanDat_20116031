package vn.edu.iuh.fit.lab_week01.respositories;

import java.sql.Connection;

public class AccountRespository {
    private Connection connection;

    public AccountRespository() throws Exception {
        connection = DBConnect.getInsance().getConn();
    }




}
