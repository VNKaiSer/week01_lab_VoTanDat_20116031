package vn.edu.iuh.fit.lab_week01.respositories;

import vn.edu.iuh.fit.lab_week01.models.GrantAccess;
import vn.edu.iuh.fit.lab_week01.models.ISGRANT;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static vn.edu.iuh.fit.lab_week01.constant.Constant.MAPISGRANT;

public class GrantAccessRespository implements IFRespository<GrantAccess> {
    private final String TABLE_NAME = "grant_access";
    private Connection connection;

    public GrantAccessRespository() throws Exception {
        connection = DBConnect.getInsance().getConn();
    }
    @Override
    public boolean insert(GrantAccess object) throws SQLException {
        String sql = "INSERT " + TABLE_NAME + "\n" + "VALUES(?,?,?,?)";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setString(1, object.getRoleId());
            ppsm.setString(2, object.getAccountId());
            ppsm.setInt(3, object.getIsGrant());
            ppsm.setString(4, object.getNote());
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        }

    }

    @Override
    public boolean update(String id, GrantAccess object) {
        String sql = "UPDATE " + TABLE_NAME +
                     " SET is_grant=?, note=?  " +
                     " WHERE role_id=? AND account_id=?";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setInt(1, object.getIsGrant());
            ppsm.setString(2, object.getNote());
            ppsm.setString(3, object.getRoleId());
            ppsm.setString(4, id);
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GrantAccess getOne(String id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE account_id=?";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setString(1, id);
            ResultSet rs = ppsm.executeQuery();
            ISGRANT isgrant = rs.getInt("is_grant") == 1 ? ISGRANT.ENABLED : ISGRANT.DISABLED;
            if (rs.next()){
                return new GrantAccess(rs.getString("role_id"),
                        rs.getString("account_id"),
                        isgrant,
                        rs.getString("note"));
            }
            return new GrantAccess();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GrantAccess> getALL() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME;
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        List<GrantAccess> grantAccesses = new ArrayList<>();
        while (rs.next()){
            ISGRANT isgrant = rs.getInt("is_grant") == 1 ? ISGRANT.ENABLED : ISGRANT.DISABLED;
            grantAccesses.add(
                    new GrantAccess(rs.getString("role_id"),
                    rs.getString("account_id"),
                    isgrant,
                    rs.getString("note")));
        }
        return grantAccesses;
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
