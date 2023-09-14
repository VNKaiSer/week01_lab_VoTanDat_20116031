package vn.edu.iuh.fit.lab_week01.models;

public enum ISGRANT {
    DISABLED(0), ENABLED(1);
    private int value;
    private ISGRANT(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
