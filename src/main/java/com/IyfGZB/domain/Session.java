package com.IyfGZB.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Session extends BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    private String tittle;

    private String description;

    private Integer courseId;

    private String speakerName;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    private String venue;

    private String iskconCenter;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<SessionStudyMaterial> studyMaterials=new HashSet<>();

    private String linkedResourceUrl;

    private String onlineRoomKey;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getIskconCenter() {
        return iskconCenter;
    }

    public void setIskconCenter(String iskconCenter) {
        this.iskconCenter = iskconCenter;
    }

    public Set<SessionStudyMaterial> getStudyMaterials() {
        return studyMaterials;
    }

    public void setStudyMaterials(Set<SessionStudyMaterial> studyMaterials) {
        this.studyMaterials = studyMaterials;
    }

    public String getLinkedResourceUrl() {
        return linkedResourceUrl;
    }

    public void setLinkedResourceUrl(String linkedResourceUrl) {
        this.linkedResourceUrl = linkedResourceUrl;
    }

    public String getOnlineRoomKey() {
        return onlineRoomKey;
    }

    public void setOnlineRoomKey(String onlineRoomKey) {
        this.onlineRoomKey = onlineRoomKey;
    }
}
