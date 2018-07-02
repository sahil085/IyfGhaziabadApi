package com.IyfGZB.controller;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.dto.SeminarDto;
import com.IyfGZB.repositories.SeminarRecordRepo;
import com.IyfGZB.services.SeminarOperation;
import com.IyfGZB.services.SeminarRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SeminarController {

    public static final Logger logger = LoggerFactory.getLogger(SeminarController.class);

    @Autowired
    private SeminarOperation seminarOperation;

    @Autowired
    private SeminarRecordService seminarRecordService;

    @GetMapping("/upcomingSeminars/{vedicLevel}/{pageNumber}/{itemPerPage}")
    public List<SeminarDto> getUpComingSeminars(@PathVariable("vedicLevel") Integer vediclevel,
                                                @PathVariable("pageNumber") Integer pageNumber,
                                                @PathVariable("itemPerPage") Integer itemPerPage){

        return seminarOperation.getUpcomingSeminars(vediclevel,pageNumber,itemPerPage);

    }

    @PostMapping("/bookSeatForSeminar/{seminarId}/{status}")
    public CommonResponseDTO bookSeatForSeminar(@PathVariable("seminarId") Long seminarId,
                                                @PathVariable("status") String status){

       return seminarRecordService.bookSeatForSeminar(seminarId, status);

    }



}
