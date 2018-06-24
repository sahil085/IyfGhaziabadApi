package com.IyfGZB.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SessionStudyMaterial {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
