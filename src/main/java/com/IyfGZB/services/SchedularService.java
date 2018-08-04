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
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    List<Seminar> seminarList = seminarRepo.findAllByDate(new Date());

    seminarList.forEach(seminar -> {
        List<SeminarAttendance> seminarAttendances = seminarAttendanceRepo.findAllBySeminar(seminar);
        logger.info("Seminar Report Starts For " + seminar.getTitle());
        googleSheetService.sendSeminarAttendanceReport(seminarAttendances);
        logger.info("Seminar Report Ends For " + seminar.getTitle());

    });
    logger.info("Attendance Report Generated For All Seminars On "+new Date());
}catch (Exception e){
    logger.error(e.getMessage());
}





    }
}
