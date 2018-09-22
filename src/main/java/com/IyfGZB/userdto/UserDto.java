package com.IyfGZB.userdto;

import com.IyfGZB.domain.Role;

import java.util.Set;

public class UserDto {

    private Integer sNo;
    private Long UserId;
    private String currentRole;
    private String classLevel;
    private String role;
    private String userName;
    private String email;
    private Long mobileNumber;
    private String attendanceStatus;
    private String attendanceMarkedBy;

    public Integer getsNo() {
        return sNo;
    }

    public void setsNo(Integer sNo) {
        this.sNo = sNo;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getAttendanceMarkedBy() {
        return attendanceMarkedBy;
    }

    public void setAttendanceMarkedBy(String attendanceMarkedBy) {
        this.attendanceMarkedBy = attendanceMarkedBy;
    }
}
