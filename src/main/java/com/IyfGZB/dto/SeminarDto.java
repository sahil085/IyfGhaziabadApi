package com.IyfGZB.dto;

import java.util.Date;

public class SeminarDto {

    private Long id ;

    private String title;

    private String venue;

    private String startTime;

    private String endTime;

    private Date date;

    private String speakerName;

    private String speakerDescription;

    private String seminarDescription;

    private String category;

    private Long totalNumberOfAvailableSeats;

    private Long totalNumberOfSeats;

    private String thumbNailUrl;

    private Boolean bookingStatus;

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

    public Long getTotalNumberOfAvailableSeats() {
        return totalNumberOfAvailableSeats;
    }

    public void setTotalNumberOfAvailableSeats(Long totalNumberOfAvailableSeats) {
        this.totalNumberOfAvailableSeats = totalNumberOfAvailableSeats;
    }

    public Long getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    public void setTotalNumberOfSeats(Long totalNumberOfSeats) {
        this.totalNumberOfSeats = totalNumberOfSeats;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public void setThumbNailUrl(String thumbNailUrl) {
        this.thumbNailUrl = thumbNailUrl;
    }

    public Boolean getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(Boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
