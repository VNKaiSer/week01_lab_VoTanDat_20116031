package vn.edu.iuh.fit.lab_week01.services.impl;

import vn.edu.iuh.fit.lab_week01.models.GrantAccess;
import vn.edu.iuh.fit.lab_week01.respositories.GrantAccessRepository;
import vn.edu.iuh.fit.lab_week01.services.GrandAccessService;

import java.sql.SQLException;

public class GrandAccessServiceImpl implements GrandAccessService {
    private final GrantAccessRepository grantAccessRespository;

    public GrandAccessServiceImpl() throws Exception {
        grantAccessRespository = new GrantAccessRepository();
    }

    @Override
    public void insertGrandAccess(GrantAccess grantAccess) throws SQLException {
        grantAccessRespository.insert(grantAccess);
    }
}
