package com.IyfGZB.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Course extends BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    private String tittle;

    private String description;

    private String vedicLevel;

    private String courseType;

    private String gender;


    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;


}
