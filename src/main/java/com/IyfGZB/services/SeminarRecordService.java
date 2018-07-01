package com.IyfGZB.services;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.controller.SeminarController;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarRecord;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.SeminarRecordRepo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.securityservices.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeminarRecordService{
        public static final Logger logger = LoggerFactory.getLogger(SeminarRecordService.class);


        @Autowired
    private SeminarRecordRepo seminarRecordRepo;

    @Autowired
    private SeminarRepo seminarRepo;

    public CommonResponseDTO bookSeatForSeminar(Long seminarId, String status){
        try{
            Seminar seminar=seminarRepo.findSeminarById(seminarId);
            UserInfo userInfo= CurrentUser.getCurrentUser();
            SeminarRecord seminarRecord= new SeminarRecord();
            seminarRecord.setSeminar(seminar);
            seminarRecord.setUser(userInfo);
            seminarRecord.setStatus(status);
            seminarRecordRepo.save(seminarRecord);
            return new CommonResponseDTO("success","Seat Booked Successfully");

        }catch (Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","OOPS..! Seat Booking Failed");
        }

    }
}
