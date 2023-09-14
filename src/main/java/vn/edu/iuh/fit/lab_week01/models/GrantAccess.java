package vn.edu.iuh.fit.lab_week01.models;

import static vn.edu.iuh.fit.lab_week01.constant.Constant.MAPISGRANT;

public class GrantAccess {
    private String roleId;
    private String accountId;
    private ISGRANT isGrant;
    private String note;
    public GrantAccess() {

    }

    public GrantAccess(String roleId, String accountId, ISGRANT isGrant, String note) {
        this.roleId = roleId;
        this.accountId = accountId;
        this.isGrant = isGrant;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getIsGrant() {
        return MAPISGRANT.get(isGrant);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public void setIsGrant(ISGRANT isGrant) {
        this.isGrant = isGrant;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "roleId='" + roleId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }
}
