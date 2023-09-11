package vn.edu.iuh.fit.lab_week01.respositories;

import vn.edu.iuh.fit.lab_week01.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public List<Account> getALL(Class<Account> clazz) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
