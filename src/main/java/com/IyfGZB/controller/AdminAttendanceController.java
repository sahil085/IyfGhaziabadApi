package com.IyfGZB.controller;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.SeminarRecord;
import com.IyfGZB.dto.SeminarRecordDTO;
import com.IyfGZB.repositories.SeminarAttendanceRepo;
import com.IyfGZB.services.SchedularService;
import com.IyfGZB.services.SeminarAttendanceOperation;
import com.IyfGZB.services.UserInfoOperation;
import com.IyfGZB.userdto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by sahil on 3/8/18.
 */

@RestController
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ADMIN','IYFVolunteer')")
@RequestMapping("/admin")
public class AdminAttendanceController {

    @Autowired
    SeminarAttendanceRepo seminarAttendanceRepo;
    @Autowired
    SeminarAttendanceOperation seminarAttendanceOperation;

    @Autowired
    SchedularService schedularService;

    @Autowired
    private UserInfoOperation userInfoOperation;


    @PutMapping("/attendance/{seminarId}/{userId}/{status}")
    public CommonResponseDTO markAttendance(@PathVariable("seminarId") Long seminarId,
                                              @PathVariable("userId") Long userId,
                                              @PathVariable("status") String status){

        return seminarAttendanceOperation.markAttendance(seminarId, userId, status);

    }

    @GetMapping("/attendanceSheet")
    public void sendAttendanceSheet(){
        schedularService.sendSeminarAttendanceSheet();
    }

    @GetMapping("/attendeeBySeminar/{seminarId}/{pageSize}/{pageIndex}")
    public Map<String, Object> getAttendeeForSeminar(@PathVariable("seminarId") Long seminarId,
                                                     @PathVariable("pageSize") Integer pageSize,
                                                     @PathVariable("pageIndex") Integer pageIndex){

        return seminarAttendanceOperation.getAttendeeForSeminar(seminarId,pageSize, pageIndex);

    }


}
