package com.IyfGZB.controller;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.services.SeminarOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminSeminarController {

    public static final Logger logger = LoggerFactory.getLogger(AdminSeminarController.class);
@Autowired
private SeminarOperation seminarOperation;

    @GetMapping("/seminarlist/{createdBy}")
    public List<Seminar> getAllSeminar(@PathVariable("createdBy") String createdBy)
    {
        return null;
    }

    @PostMapping("/createseminar")
    public ResponseEntity<?> createSeminar(@RequestBody Seminar seminar)
    {
        try{

            return new ResponseEntity<>(seminarOperation.createSeminar(seminar),HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO("danger",
                    "OOPS..!! Seminar Could Not Created Please Try Again"),HttpStatus.OK);

        }

    }



}
