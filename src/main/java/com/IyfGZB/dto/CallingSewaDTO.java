package com.IyfGZB.dto;

/**
 * Created by sahil on 23/9/18.
 */
public class CallingSewaDTO {

    private String userName;
    private String emailId;
    private Long mobileNumber;
    private String currentStatus;
    private String status;
    private String classlevel;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClasslevel() {
        return classlevel;
    }

    public void setClasslevel(String classlevel) {
        this.classlevel = classlevel;
    }
}
