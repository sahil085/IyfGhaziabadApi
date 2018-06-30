package com.IyfGZB.controller;


import com.IyfGZB.domain.Seminar;
import com.IyfGZB.services.SeminarOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SeminarController {

    public static final Logger logger = LoggerFactory.getLogger(AdminSeminarController.class);

    @Autowired
    private SeminarOperation seminarOperation;

    @GetMapping("/upcomingSeminars/{vedicLevel}/{pageNumber}/{itemPerPage}")
    public List<Seminar> getUpComingSeminars(@PathVariable("vedicLevel") Integer vediclevel,
                                             @PathVariable("pageNumber") Integer pageNumber,
                                             @PathVariable("itemPerPage") Integer itemPerPage){
        return seminarOperation.getUpcomingSeminars(vediclevel,pageNumber,itemPerPage);
    }


}
