package vn.edu.iuh.fit.lab_week01.services;

import vn.edu.iuh.fit.lab_week01.models.GrantAccess;

import java.sql.SQLException;

public interface GrandAccessService {
    void insertGrandAccess(GrantAccess grantAccess) throws SQLException;

}
