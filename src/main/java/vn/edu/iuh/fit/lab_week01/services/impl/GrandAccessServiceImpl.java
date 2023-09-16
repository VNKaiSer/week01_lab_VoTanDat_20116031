package vn.edu.iuh.fit.lab_week01.services.impl;

import vn.edu.iuh.fit.lab_week01.models.GrantAccess;
import vn.edu.iuh.fit.lab_week01.respositories.GrantAccessRespository;
import vn.edu.iuh.fit.lab_week01.services.GrandAccessService;

import java.sql.SQLException;

public class GrandAccessServiceImpl implements GrandAccessService {
    private final GrantAccessRespository grantAccessRespository;

    public GrandAccessServiceImpl() throws Exception {
        grantAccessRespository = new GrantAccessRespository();
    }

    @Override
    public void insertGrandAccess(GrantAccess grantAccess) throws SQLException {
        grantAccessRespository.insert(grantAccess);
    }
}
