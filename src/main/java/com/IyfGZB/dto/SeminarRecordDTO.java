package com.IyfGZB.dto;

import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarRecord;

import java.util.List;

/**
 * Created by sahil on 2/8/18.
 */
public class SeminarRecordDTO {

    private Seminar seminar;
    private List<SeminarRecord> seminarRecord;


    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }

    public List<SeminarRecord> getSeminarRecord() {
        return seminarRecord;
    }

    public void setSeminarRecord(List<SeminarRecord> seminarRecord) {
        this.seminarRecord = seminarRecord;
    }
}
