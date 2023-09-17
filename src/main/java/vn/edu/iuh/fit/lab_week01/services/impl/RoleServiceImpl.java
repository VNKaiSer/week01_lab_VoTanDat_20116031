package vn.edu.iuh.fit.lab_week01.services.impl;

import vn.edu.iuh.fit.lab_week01.models.Role;
import vn.edu.iuh.fit.lab_week01.respositories.RoleRepository;
import vn.edu.iuh.fit.lab_week01.services.RoleService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl() throws Exception {
        roleRepository = new RoleRepository();
    }



    @Override
    public List<Role> getAllRole() throws SQLException {
        return roleRepository.getALL();
    }

    @Override
    public List<Role> getRolesFromAccount(String accountId) throws SQLException {
        List<String> roleString = roleRepository.getRolesFromAccount(accountId);
        System.out.println(roleString);
        List<Role> roleList = new ArrayList<>();
        for (String role:
             roleString) {
            roleList.add(roleRepository.getOne(role));
        }
        return roleList;
    }
}
