package vn.edu.iuh.fit.lab_week01.respositories;

import vn.edu.iuh.fit.lab_week01.models.Account;

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
    public boolean insert(Account object) throws SQLException {
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
            if (rs.next()){
                return new Account(rs.getString("account_id"),
                                    rs.getString("full_name"),
                                    rs.getString("password"),
                                    rs.getString("email"),
                                    rs.getString("phone"),
                                    rs.getInt("status"));
            }
            return new Account();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getALL(Class<Account> clazz) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME;
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        List<Account> accounts = new ArrayList<>();
        while (rs.next()){
            Account tmpAccount = new Account(rs.getString("account_id"),
                    rs.getString("full_name"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getInt("status"));
            accounts.add(tmpAccount);
        }
        return accounts;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE account_id=?";
        try (PreparedStatement ppsm = connection.prepareStatement(sql)) {
            ppsm.setString(1, id);
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
