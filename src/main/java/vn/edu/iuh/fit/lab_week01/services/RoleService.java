package vn.edu.iuh.fit.lab_week01.services;

import vn.edu.iuh.fit.lab_week01.models.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {
    List<Role> getAllRole() throws SQLException;

    List<Role> getRolesFromAccount(String accountId) throws SQLException;
 }
