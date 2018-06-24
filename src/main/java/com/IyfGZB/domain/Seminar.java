package com.IyfGZB.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Seminar extends BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    private String tittle;

    private String venue;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String speakerName;

    private String speakerDescription;

    private String seminarDescription;

    private String category;

    private Integer TotalNumberOfAvailableSeats;

    private Integer TotalNumberOfSeats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
