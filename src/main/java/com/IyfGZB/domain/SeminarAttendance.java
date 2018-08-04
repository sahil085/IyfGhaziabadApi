package com.IyfGZB.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sahil on 3/8/18.
 */
@Entity
public class SeminarAttendance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    private Seminar seminar;

    private String attendanceStatus;

    @OneToOne
    private UserInfo user;

    private String attendanceMarkedBy;

    private String attendanceUpdatedBy;

    @Column(name = "DATE_CREATED",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

//    @Column(name = "DATE_MODIFIED",nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getAttendanceMarkedBy() {
        return attendanceMarkedBy;
    }

    public void setAttendanceMarkedBy(String attendanceMarkedBy) {
        this.attendanceMarkedBy = attendanceMarkedBy;
    }

    public String getAttendanceUpdatedBy() {
        return attendanceUpdatedBy;
    }

    public void setAttendanceUpdatedBy(String attendanceUpdatedBy) {
        this.attendanceUpdatedBy = attendanceUpdatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

//    public Date getModifiedDate() {
//        return modifiedDate;
//    }
//
//    public void setModifiedDate(Date modifiedDate) {
//        this.modifiedDate = modifiedDate;
//    }
}
