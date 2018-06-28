package com.IyfGZB.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Course  implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String tittle;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String vedicLevel;


    @Column(nullable = false)
    private String courseType;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String duration;


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

    public String getVedicLevel() {
        return vedicLevel;
    }

    public void setVedicLevel(String vedicLevel) {
        this.vedicLevel = vedicLevel;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
