package model;

import java.io.Serializable;

public class Schedule implements Serializable{
    private String date;
    private String time;
    private String registrationTime;
    private String passportNumber;
    private String comment;

    public Schedule() {
        this.date = "";
        this.time = "";
        this.registrationTime = "";
        this.passportNumber = "";
        this.comment = "";
    }

    public Schedule(String date, String time, String registrationTime, String passportNumber, String comment) {
        this.date = date;
        this.time = time;
        this.registrationTime = registrationTime;
        this.passportNumber = passportNumber;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
