package com.IyfGZB.dto;

import java.util.Date;

/**
 * Created by sahil on 3/8/18.
 */
public class SeminarAttendanceDTO {


    private String seminarTitle;
    private String speakerName;
    private Date date;
    private String userName;
    private String status;

    public String getSeminarTitle() {
        return seminarTitle;
    }

    public void setSeminarTitle(String seminarTitle) {
        this.seminarTitle = seminarTitle;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
