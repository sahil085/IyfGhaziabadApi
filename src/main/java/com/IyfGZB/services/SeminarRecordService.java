package com.IyfGZB.services;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.constants.SeminarConstant;
import com.IyfGZB.controller.SeminarController;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarRecord;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.SeminarRecordRepo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.securityservices.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeminarRecordService{
        public static final Logger logger = LoggerFactory.getLogger(SeminarRecordService.class);


    @Autowired
    private SeminarRecordRepo seminarRecordRepo;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SeminarRepo seminarRepo;

    @Transactional
    public CommonResponseDTO bookSeatForSeminar(Long seminarId, String status, String email){
        try{
            Seminar seminar=seminarRepo.findSeminarById(seminarId);
            UserInfo userInfo;
            if(email!=null){
                userInfo = userInfoRepository.findByEmail(email);
            }else {
                userInfo= CurrentUser.getCurrentUser();
            }
            seminarRecordRepo.deleteSeminarRecordBySeminarAndUser(seminar,userInfo);
            SeminarRecord seminarRecord= new SeminarRecord();
            seminarRecord.setSeminar(seminar);
            seminarRecord.setUser(userInfo);
            seminarRecord.setResponse(status);
            seminarRecord.setStatus(SeminarConstant.STATUS_BOOKED);
            seminarRecordRepo.save(seminarRecord);
            return new CommonResponseDTO("success","Seat Booked Successfully");

        }catch (Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","OOPS..! Seat Booking Failed");
        }

    }

    public CommonResponseDTO cancelseatForSeminar(Long seminarRecordId, String reason){
        try {

            SeminarRecord seminarRecord=seminarRecordRepo.findSeminarRecordById(seminarRecordId);
            seminarRecord.setResponse(reason);
            seminarRecord.setStatus(SeminarConstant.STATUS_CANCELED);
            seminarRecordRepo.save(seminarRecord);
            return new CommonResponseDTO("success","Seat Cancelled Successfully");
        }catch(Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","OOPS..! Seat Cancelation Failed");
        }
    }


}
