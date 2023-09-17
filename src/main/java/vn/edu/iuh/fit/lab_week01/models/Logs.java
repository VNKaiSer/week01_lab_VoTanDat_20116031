package vn.edu.iuh.fit.lab_week01.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Logs {
    private long logId;
    private String acountId;
    private LocalDateTime logInDate;
    private LocalDateTime logOutDate;
    private String notes;

    public Logs() {
    }

    public Logs(String acountId, LocalDateTime logInDate, LocalDateTime logOutDate, String notes) {
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

    public LocalDateTime getLogInDate() {
        return logInDate;
    }

    public void setLogInDate(LocalDateTime logInDate) {
        this.logInDate = logInDate;
    }

    public LocalDateTime getLogOutDate() {
        return logOutDate;
    }

    public void setLogOutDate(LocalDateTime logOutDate) {
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
