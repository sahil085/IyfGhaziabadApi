package com.IyfGZB.services;

import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarAttendance;
import com.IyfGZB.domain.SeminarRecord;
import com.IyfGZB.repositories.SeminarAttendanceRepo;
import com.IyfGZB.repositories.SeminarRecordRepo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.util.ExcelMaker;
import com.IyfGZB.util.GoogleSheetService;
import org.joda.time.DateTimeComparator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
    GoogleSheetService googleSheetService;

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(SchedularService.class);



    public void sendSeminarAttendanceSheet(){
try {
    System.out.println( " --- " +new Date());
    List<Seminar> seminarList = seminarRepo.findAll();
    DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
    seminarList.forEach(seminar -> {

        if(dateTimeComparator.compare(seminar.getDate(),new Date()) == 0){
            List<SeminarAttendance> seminarAttendances = seminarAttendanceRepo.findAllBySeminar(seminar);
            logger.info("Seminar Report Starts For " + seminar.getTitle());
            if(!seminarAttendances.isEmpty()){
                googleSheetService.sendSeminarAttendanceReport(seminarAttendances);
            }
            logger.info("Seminar Report Ends For " + seminar.getTitle());
        }


    });
    logger.info("Attendance Report Generated For All Seminars On "+new Date());
}catch (Exception e){
    System.out.println("-----------------");
    logger.error(e.getMessage());
}

    }
}
