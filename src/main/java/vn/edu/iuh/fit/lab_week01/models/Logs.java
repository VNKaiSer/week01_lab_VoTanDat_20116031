package vn.edu.iuh.fit.lab_week01.models;

import java.util.Date;

public class Logs {
    private long logId;
    private String acountId;
    private Date logInDate;
    private Date logOutDate;
    private String notes;

    public Logs() {
    }

    public Logs(String acountId, Date logInDate, Date logOutDate, String notes) {
        this.acountId = acountId;
        this.logInDate = logInDate;
        this.logOutDate = logOutDate;
        this.notes = notes;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public String getAcountId() {
        return acountId;
    }

    public void setAcountId(String acountId) {
        this.acountId = acountId;
    }

    public String getLogInDate() {
        return String.valueOf(logInDate);
    }

    public void setLogInDate(Date logInDate) {
        this.logInDate = logInDate;
    }

    public String getLogOutDate() {
        return String.valueOf(logOutDate);
    }

    public void setLogOutDate(Date logOutDate) {
        this.logOutDate = logOutDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "logId=" + logId +
                ", acountId='" + acountId + '\'' +
                ", logInDate=" + logInDate +
                ", logOutDate=" + logOutDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}
