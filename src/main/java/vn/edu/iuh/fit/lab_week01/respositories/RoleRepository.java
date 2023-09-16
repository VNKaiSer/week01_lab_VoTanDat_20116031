package vn.edu.iuh.fit.lab_week01.respositories;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.lab_week01.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository implements IFRespository<Role> {

    private Connection connection;
    private final String TABLE_NAME = "role";

     public RoleRepository() throws Exception {
        connection = DBConnect.getInsance().getConn();
    }

    @Override
    public boolean insert(Role object) throws SQLException {
        String sql = "INSERT " + TABLE_NAME + "\n" + "VALUES(?,?,?)";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setString(1, object.getRoleId());
            ppsm.setString(2, object.getRoleName());
            ppsm.setString(3, object.getDescription());
            ppsm.setInt(4, object.getStatus());
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean update(String id, Role object) {
        String sql = "UPDATE " + TABLE_NAME + " SET role_name=?, status=?, description = ?  WHERE role_id=?";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setString(1, object.getRoleName());
            ppsm.setInt(2, object.getStatus());
            ppsm.setString(3, object.getDescription());
            ppsm.setString(4, id);
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Role getOne(String id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE role_id=?";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setString(1, id);
            ResultSet rs = ppsm.executeQuery();
            if (rs.next()){
                return new Role(rs.getString("role_id"),
                                rs.getString("role_name"),
                                rs.getString("description"),
                                rs.getInt("status"));
            }
            return new Role();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> getALL() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME;
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        List<Role> roles = new ArrayList<>();
        while (rs.next()){
            roles.add(new Role(rs.getString("role_id"),
                                rs.getString("role_name"),
                                rs.getString("description"),
                                rs.getInt("status")));
        }
        return roles;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE role_id=?";
        try(PreparedStatement ppsm = connection.prepareStatement(sql)){
            ppsm.setString(1, id);
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
