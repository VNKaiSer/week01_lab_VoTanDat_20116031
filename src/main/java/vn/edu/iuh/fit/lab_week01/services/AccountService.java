package vn.edu.iuh.fit.lab_week01.services;

public interface AccountService {
    boolean login(String username, String password);
     boolean register(String username, String password, String email, String phone);


}
