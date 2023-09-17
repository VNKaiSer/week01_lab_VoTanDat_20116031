package vn.edu.iuh.fit.lab_week01.respositories;

import vn.edu.iuh.fit.lab_week01.models.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LogRepository implements IFRespository<Logs> {
    private final String TABLE_NAME = "log";
    private final Connection connection;

    public LogRepository() throws Exception {
        connection = DBConnect.getInsance().getConn();
    }
    @Override
    public boolean insert(Logs object) throws SQLException {
        String sql = "INSERT " + TABLE_NAME + "\n" + "VALUES(NULL,?,?,?,?)";
        String dateLogin = object.getLogInDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String dateLogout = object.getLogOutDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (PreparedStatement ppsm = connection.prepareStatement(sql)) {
            ppsm.setString(1, object.getAcountId());
            ppsm.setString(2, dateLogin);
            ppsm.setString(3, dateLogout);
            ppsm.setString(4, object.getNotes());
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean update(String id, Logs object) {
        String sql = "UPDATE " + TABLE_NAME + " SET log_in_date=?, log_out_date=?, notes = ?  WHERE account_id=?";
        String dateLogin = object.getLogInDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String dateLogout = object.getLogOutDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        try (PreparedStatement ppsm = connection.prepareStatement(sql)) {
            ppsm.setString(1, dateLogin);
            ppsm.setString(2, dateLogout);
            ppsm.setString(3, object.getNotes());
            ppsm.setString(4, id);
            int rowsAffected = ppsm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Logs getOne(String id) {
        return null;
    }

    @Override
    public List<Logs> getALL() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
