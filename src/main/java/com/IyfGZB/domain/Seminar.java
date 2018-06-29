package com.IyfGZB.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Seminar  implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

@Column(nullable = false)
    private String title;
    @Column(nullable = false)

    private String venue;

    @Column(nullable = false)

    private String startTime;
    @Column(nullable = false)

    private String endTime;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)

    private Date date;


    @Column(name = "CREATED_BY",nullable = false)
    private String createdBy;

    @Column(name = "MODIFIED_BY",nullable = false)
    private String modifiedBy;

    @Column(name = "DATE_CREATED",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "DATE_MODIFIED",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;



    private String speakerName;

    private String speakerDescription;

    private String seminarDescription;

    private String category;

    private Integer TotalNumberOfAvailableSeats;

    private Integer TotalNumberOfSeats;


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getSpeakerDescription() {
        return speakerDescription;
    }

    public void setSpeakerDescription(String speakerDescription) {
        this.speakerDescription = speakerDescription;
    }

    public String getSeminarDescription() {
        return seminarDescription;
    }

    public void setSeminarDescription(String seminarDescription) {
        this.seminarDescription = seminarDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getTotalNumberOfAvailableSeats() {
        return TotalNumberOfAvailableSeats;
    }

    public void setTotalNumberOfAvailableSeats(Integer totalNumberOfAvailableSeats) {
        TotalNumberOfAvailableSeats = totalNumberOfAvailableSeats;
    }

    public Integer getTotalNumberOfSeats() {
        return TotalNumberOfSeats;
    }

    public void setTotalNumberOfSeats(Integer totalNumberOfSeats) {
        TotalNumberOfSeats = totalNumberOfSeats;
    }
}
