package vn.edu.iuh.fit.lab_week01.models;

public enum STATUS {

    ACTIVE(1), DEACTIVE(0), DELETED(-1);
    private int value;
    STATUS(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

}
