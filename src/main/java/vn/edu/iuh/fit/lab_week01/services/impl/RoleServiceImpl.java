package vn.edu.iuh.fit.lab_week01.services.impl;

import jakarta.inject.Inject;
import vn.edu.iuh.fit.lab_week01.models.Role;
import vn.edu.iuh.fit.lab_week01.respositories.RoleRepository;
import vn.edu.iuh.fit.lab_week01.services.RoleService;

import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRespository;

    public RoleServiceImpl() throws Exception {
        roleRespository = new RoleRepository();
    }



    @Override
    public List<Role> getAllRole() throws SQLException {
        return roleRespository.getALL();
    }
}
