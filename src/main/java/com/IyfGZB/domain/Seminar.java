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





}
