package com.IyfGZB.controller;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.services.SchedularService;
import com.IyfGZB.services.SeminarAttendanceOperation;
import com.IyfGZB.services.SeminarOperation;
import com.IyfGZB.services.SeminarRecordService;
import com.IyfGZB.util.CustomExcelMaker;
import com.IyfGZB.util.MasterAttendanceExcelMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class SeminarController {

    public static final Logger logger = LoggerFactory.getLogger(SeminarController.class);

    @Autowired
    private SeminarOperation seminarOperation;

    @Autowired
    private SeminarRecordService seminarRecordService;

    @Autowired
    private SchedularService schedularService;
    @Autowired
    private CustomExcelMaker customExcelMaker;

    @Autowired
    private SeminarAttendanceOperation seminarAttendanceOperation;

    @GetMapping("/sheet/{seminarId}")
    public void sendSheet(@PathVariable(value = "seminarId") Long seminarId){
        schedularService.sendSeminarAttendanceSheet(seminarId);
    }


    @GetMapping("/masterSheet/{category}")
    public String generateMasterSheet(@PathVariable("category") String category){
        return seminarAttendanceOperation.generateMasterSheet(category);

    }
    @GetMapping("/populateAttendance")
    public String populateAttendance(){
       return seminarAttendanceOperation.populateSeminarAttendance();
    }
    @GetMapping("/upcomingSeminars/{vedicLevel}/{pageNumber}/{itemPerPage}")
    public HashMap<String,Object> getUpComingSeminars(@PathVariable("vedicLevel") Integer vediclevel,
                                                        @PathVariable("pageNumber") Integer pageNumber,
                                                        @PathVariable("itemPerPage") Integer itemPerPage){

        return seminarOperation.getUpcomingSeminars(vediclevel,pageNumber,itemPerPage);

    }

    @PostMapping("/bookSeatForSeminar/{seminarId}/{status}/{email}")
    public CommonResponseDTO bookSeatForSeminar(@PathVariable("seminarId") Long seminarId,
                                                @PathVariable("status") String status,
                                                @PathVariable("email") String email){

       return seminarRecordService.bookSeatForSeminar(seminarId, status,email);

    }

    @PutMapping("/cancelSeatForSeminar/{seminarRecordId}/{reason}/{email}")
    public CommonResponseDTO cancelSeatForSeminar(@PathVariable("reason") String reason,
                                                  @PathVariable("seminarRecordId") Long seminarRecordId){
        return seminarRecordService.cancelseatForSeminar(seminarRecordId,reason);
    }
//    @GetMapping("/gettotalPage/{itemsPerPage}")
//    public Integer getTotalNumberOfpages(@PathVariable("itemsPerPage") Integer itemPerPage){
//        seminarRecordSer
//    }



}
