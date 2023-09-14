package vn.edu.iuh.fit.lab_week01.services;

import java.sql.SQLException;

public interface AccountService {
    int login(String username, String password) throws SQLException;
     boolean register(String username, String password, String email, String phone);


}
