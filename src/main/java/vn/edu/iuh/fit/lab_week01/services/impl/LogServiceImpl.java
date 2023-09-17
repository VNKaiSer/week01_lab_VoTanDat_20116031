package vn.edu.iuh.fit.lab_week01.services.impl;


import vn.edu.iuh.fit.lab_week01.models.Logs;
import vn.edu.iuh.fit.lab_week01.respositories.LogRepository;
import vn.edu.iuh.fit.lab_week01.services.LogService;

import java.sql.SQLException;

public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    public LogServiceImpl() throws Exception {
        logRepository = new LogRepository();
    }
    @Override
    public boolean insertLogs(Logs object) throws SQLException {
        return logRepository.insert(object);
    }

    @Override
    public boolean update(String id, Logs object) {
        return logRepository.update(id, object);
    }
}
