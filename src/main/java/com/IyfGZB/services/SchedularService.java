package com.IyfGZB.services;

import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarRecord;
import com.IyfGZB.repositories.SeminarAttendanceRepo;
import com.IyfGZB.repositories.SeminarRecordRepo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.util.ExcelMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sahil on 3/8/18.
 */

@Service
public class SchedularService {

    @Autowired
    SeminarAttendanceRepo seminarAttendanceRepo;
    @Autowired
    SeminarRepo seminarRepo;
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    SeminarRecordRepo seminarRecordRepo;

    @Autowired
    ExcelMaker excelMaker;

    public void sendSeminarAttendanceSheet(){

        List<Seminar> seminarList = seminarRepo.findAllByDate(new Date());

        seminarList.forEach( seminar -> {
            List<SeminarRecord> seminarRecordList = seminarRecordRepo.findAllByBySeminar(seminar);
            excelMaker.makeAttendanceSheet(seminar,seminarRecordList);
        });




    }
}
