package com.IyfGZB.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by sahil on 23/9/18.
 */
@Entity
public class CallingSewa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    private UserInfo user;
    private UserInfo volunteer;
    private Seminar seminar;
    private Boolean isCalled;
    private String responseStatus;

    private Date sewaAllocationDate;
    private Date sewaCompletionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public UserInfo getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(UserInfo volunteer) {
        this.volunteer = volunteer;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }

    public Boolean getCalled() {
        return isCalled;
    }

    public void setCalled(Boolean called) {
        isCalled = called;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Date getSewaAllocationDate() {
        return sewaAllocationDate;
    }

    public void setSewaAllocationDate(Date sewaAllocationDate) {
        this.sewaAllocationDate = sewaAllocationDate;
    }

    public Date getSewaCompletionDate() {
        return sewaCompletionDate;
    }

    public void setSewaCompletionDate(Date sewaCompletionDate) {
        this.sewaCompletionDate = sewaCompletionDate;
    }
}
