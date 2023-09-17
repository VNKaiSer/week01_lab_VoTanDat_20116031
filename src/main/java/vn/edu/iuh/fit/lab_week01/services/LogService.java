package vn.edu.iuh.fit.lab_week01.services;

import vn.edu.iuh.fit.lab_week01.models.Logs;

import java.sql.SQLException;

public interface LogService {
    boolean insertLogs(Logs object) throws SQLException;
    boolean update(String id, Logs object);

}
