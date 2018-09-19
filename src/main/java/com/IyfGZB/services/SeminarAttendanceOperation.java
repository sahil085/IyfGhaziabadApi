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
import com.IyfGZB.userdto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public CommonResponseDTO markAttendance(Long seminarId, Long userId, String status) {
        try {
            Seminar seminar = seminarRepo.findSeminarById(seminarId);


            SeminarAttendance attendance = seminarAttendanceRepo.findSeminarAttendanceBySeminarAndUserId(seminar, userId);
            if (attendance != null) {
                UserInfo userInfo = userInfoRepository.findById(userId).get();
                attendance.setSeminar(seminar);
                attendance.setUser(userInfo);
                String userEmail = CurrentUser.getCurrentUser().getEmail();
                attendance.setAttendanceMarkedBy(userEmail);
                attendance.setCreatedDate(new Date());
                if (status.equals(AttendanceConstant.MARK_PRESENT)) {
                    attendance.setAttendanceStatus(AttendanceConstant.MARK_PRESENT);
                    seminarAttendanceRepo.save(attendance);
                    return new CommonResponseDTO("success", "User marked as present");


                } else {
                    attendance.setAttendanceStatus(AttendanceConstant.MARK_ABSENT);
                    seminarAttendanceRepo.save(attendance);
                    return new CommonResponseDTO("success", "User marked as absent");

                }
            } else {
                SeminarAttendance attendance1 = new SeminarAttendance();
                UserInfo userInfo = userInfoRepository.findById(userId).get();

                attendance1.setSeminar(seminar);
                attendance1.setUser(userInfo);
                String userEmail = CurrentUser.getCurrentUser().getEmail();
                attendance1.setAttendanceMarkedBy(userEmail);
                attendance1.setCreatedDate(new Date());
                if (status.equals(AttendanceConstant.MARK_PRESENT)) {
                    attendance1.setAttendanceStatus(AttendanceConstant.MARK_PRESENT);
                    seminarAttendanceRepo.save(attendance1);
                    return new CommonResponseDTO("success", "User marked as present");


                } else {
                    attendance1.setAttendanceStatus(AttendanceConstant.MARK_ABSENT);
                    seminarAttendanceRepo.save(attendance1);
                    return new CommonResponseDTO("success", "User marked as absent");

                }

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger", "Try again");
        }


    }

    public  Map<String, Object> getAttendeeForSeminar(Long seminarId, Integer itemPerPage, Integer pageNumber){

        Seminar seminar = seminarRepo.findSeminarById(seminarId);
        PageRequest pageRequest=new PageRequest(pageNumber,itemPerPage, Sort.Direction.DESC,"createdDate");
        Map<String, Object> data = new HashMap<>();
        Page<SeminarAttendance> seminarAttendanceList = seminarAttendanceRepo.findAllBySeminar(seminar,pageRequest);
        List<UserDto> userDtoList = new ArrayList<>();
        seminarAttendanceList.getContent().forEach(seminarAttendance -> {
            UserDto userDto = new UserDto();
            userDto.setClassLevel(seminarAttendance.getUser().getClassLevel());
            userDto.setUserName(seminarAttendance.getUser().getUsername());
            userDto.setMobileNumber(seminarAttendance.getUser().getMobileNumber());
            userDto.setAttendanceStatus(seminarAttendance.getAttendanceStatus());
            userDto.setAttendanceMarkedBy(seminarAttendance.getAttendanceMarkedBy());
            userDtoList.add(userDto);
        });
        data.put("attendeeList",userDtoList);
        data.put("totalElements",seminarAttendanceList.getTotalElements());
        return data;
    }


}
