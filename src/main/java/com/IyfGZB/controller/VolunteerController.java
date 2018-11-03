package com.IyfGZB.controller;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.services.SchedularService;
import com.IyfGZB.services.VolunteerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class VolunteerController {

    public static final Logger logger = LoggerFactory.getLogger(VolunteerController.class);

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    SchedularService schedularService;

    @PostMapping("/registeruser")
    public CommonResponseDTO registerNewUser(@RequestBody UserInfo userInfo){

    return volunteerService.registrationService(userInfo);

    }

//    @GetMapping("/attendanceSheet")
//    public void sendAttendanceSheet(){
//        schedularService.sendSeminarAttendanceSheet();
//    }

}
