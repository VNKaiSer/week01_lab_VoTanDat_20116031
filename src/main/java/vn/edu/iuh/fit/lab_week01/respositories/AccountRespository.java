package vn.edu.iuh.fit.lab_week01.respositories;

import vn.edu.iuh.fit.lab_week01.models.Account;
import vn.edu.iuh.fit.lab_week01.models.STATUS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRespository implements IFRespository<Account> {
    private Connection connection;
    private final String TABLE_NAME = "account";

    public AccountRespository() throws Exception {
        connection = DBConnect.getInsance().getConn();
    }


    @Override
    public boolean insert(Account object)  {
        String sql = "INSERT " + TABLE_NAME + "\n" +
                     "VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ppsm = connection.prepareStatement(sql)) {
            ppsm.setString(1, object.getAccountId());
            ppsm.setString(2, object.getFullName());
            ppsm.setString(3, object.getPassword());
            ppsm.setString(4, object.getEmail());
            ppsm.setString(5, object.getPhone());
            ppsm.setInt(6, object.getStatus());

            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(String id, Account object) {
        String sql = "UPDATE " + TABLE_NAME + " SET full_name=?, password=?, email=?, phone=?,status=?  WHERE account_id=?";

        try (PreparedStatement ppsm = connection.prepareStatement(sql)) {
            ppsm.setString(1, object.getFullName());
            ppsm.setString(2, object.getPassword());
            ppsm.setString(3, object.getEmail());
            ppsm.setString(4, object.getPhone());
            ppsm.setInt( 5, object.getStatus());
            ppsm.setString(6, id);

            int rowsAffected = ppsm.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Account getOne(String id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE account_id=?";
        try (PreparedStatement ppsm = connection.prepareStatement(sql)) {
            ppsm.setString(1, id);
            ResultSet rs = ppsm.executeQuery();
            STATUS status;
            if (rs.next()){
                if (rs.getInt("status") == 0){
                    status = STATUS.DEACTIVE;
                } else if (rs.getInt("status") == 1) {
                    status = STATUS.ACTIVE;
                } else {
                    status = STATUS.DELETED;
                }
                return new Account(rs.getString("account_id"),
                                    rs.getString("full_name"),
                                    rs.getString("password"),
                                    rs.getString("email"),
                                    rs.getString("phone"),
                                    status);
            }
            return new Account();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getALL() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME;
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        List<Account> accounts = new ArrayList<>();
        while (rs.next()){
            STATUS status;
            if (rs.getInt("status") == 0){
                status = STATUS.DEACTIVE;
            } else if (rs.getInt("status") == 1) {
                status = STATUS.ACTIVE;
            } else {
                status = STATUS.DELETED;
            }
            Account tmpAccount = new Account(rs.getString("account_id"),
                    rs.getString("full_name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    status);
            accounts.add(tmpAccount);
        }
        return accounts;
    }

    @Override
    public boolean delete(String id) {
        String sql = "UPDATE " + TABLE_NAME + " SET STATUS=-1 WHERE account_id=?";
        try (PreparedStatement ppsm = connection.prepareStatement(sql)) {
            ppsm.setString(1, id);
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Authenticates a user by checking if the provided username and password match an active account in the database.
     *
     * @param  username  the username of the user
     * @param  password  the password of the user
     * @return           an integer value representing the authentication result:
     *                   - 1: if the user is an admin
     *                   - 0: if the user is not an admin
     *                   - 2: if the user account is not activated
     *                   - (-1): if the user account has been deleted
     *                   - (-2): if the username or password is incorrect
     */
    public int login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE account_id=? AND password=? AND status=1";
        PreparedStatement ppsm = connection.prepareStatement(sql);
        ppsm.setString(1, username);
        ppsm.setString(2, password);
        ResultSet rs = ppsm.executeQuery();
        if (rs.next()) {
            // kiểm tra tài khoản có bị khoá không
            int status = rs.getInt("status");
            if (status == 1) { // còn kích hoạt
                if (isAdmin(username)) {
                    return 1;
                }
                return 0; // không có quyền admin
            } else if (status == 0) {
                return 2; // không kích hoạt
            } else if (status == -1) {
                return -1; // đã bị xoá hoặc
            }
        } else { // tài khoản mật khẩu không chính xác
            return -2;
        }
        return -2;
    }

    /**
     * Checks if the given account ID belongs to an admin.
     *
     * @param  accountId    the ID of the account to check
     * @return              true if the account is an admin, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    public boolean isAdmin(String accountId) throws SQLException {
        String sql =
                "SELECT role_name\n" +
                "FROM role\n" +
                "WHERE role_id IN (\n" +
                "\tSELECT role_id \n" +
                "\tFROM grant_access\n" +
                "\tWHERE account_id = ?\n and is_grant=1" +
                ")";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setString(1, accountId);
            ResultSet rs = ppsm.executeQuery();
            return rs.next() && rs.getString("role_name").equals("admin");
        }
    }

    public List<String> getAccountsFromRole(String accountId) throws SQLException {
        String sql = "SELECT account_id \n" +
                "FROM account\n" +
                "WHERE account_id IN(\n" +
                "\tSELECT account_id \n" +
                "\tFROM grant_access\n" +
                "\tWHERE role_id = \"admin\"\n" +
                ")";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setString(1, accountId);
            ResultSet rs = ppsm.executeQuery();
            List<String> accounts = new ArrayList<>();
            while (rs.next()){
                accounts.add(rs.getString("account_id"));
            }
            return accounts;
        }
    }
}
