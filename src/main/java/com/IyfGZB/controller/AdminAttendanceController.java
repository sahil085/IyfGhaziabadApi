package com.IyfGZB.controller;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.SeminarRecord;
import com.IyfGZB.dto.SeminarRecordDTO;
import com.IyfGZB.repositories.SeminarAttendanceRepo;
import com.IyfGZB.services.SeminarAttendanceOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sahil on 3/8/18.
 */

@RestController
@CrossOrigin
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminAttendanceController {

    @Autowired
    SeminarAttendanceRepo seminarAttendanceRepo;
    @Autowired
    SeminarAttendanceOperation seminarAttendanceOperation;




    @PutMapping("/attendance/{seminarId}/{userId}/{status}")
    public CommonResponseDTO getSeminarRecord(@PathVariable("seminarId") Long seminarId,
                                              @PathVariable("userId") Long userId,
                                              @PathVariable("status") String status){

        return seminarAttendanceOperation.markAttendance(seminarId, userId, status);

    }


}
