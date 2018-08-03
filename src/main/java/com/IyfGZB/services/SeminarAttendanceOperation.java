package com.IyfGZB.services;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.constants.AttendanceConstant;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarAttendance;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.dto.SeminarAttendanceDTO;
import com.IyfGZB.repositories.SeminarAttendanceRepo;
import com.IyfGZB.repositories.SeminarRepo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.securityservices.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by sahil on 3/8/18.
 */

@Service
public class SeminarAttendanceOperation {

    public static final Logger logger = LoggerFactory.getLogger(SeminarAttendanceOperation.class);

    @Autowired
    SeminarAttendanceRepo seminarAttendanceRepo;
    @Autowired
    SeminarRepo seminarRepo;
    @Autowired
    UserInfoRepository userInfoRepository;

    public CommonResponseDTO markAttendance(Long seminarId,Long userId,String status){
        try{

            UserInfo userInfo = userInfoRepository.findById(userId).get();
            Seminar seminar = seminarRepo.findSeminarById(seminarId);
            SeminarAttendance attendance= new SeminarAttendance();
            attendance.setSeminar(seminar);
            attendance.setUser(userInfo);
            String userEmail = CurrentUser.getCurrentUser().getEmail();
            attendance.setAttendanceMarkedBy(userEmail);
            attendance.setCreatedDate(new Date());
            if(status.equals(AttendanceConstant.MARK_PRESENT)){
                attendance.setAttendanceStatus(AttendanceConstant.MARK_PRESENT);

            }else {
                attendance.setAttendanceStatus(AttendanceConstant.MARK_ABSENT);
            }
            return new CommonResponseDTO("success","User marked as present");

        }catch (Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","Try again");
        }


    }

    public


}
