package com.IyfGZB.controller;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.dto.SeminarRecordDTO;
import com.IyfGZB.services.SeminarOperation;
import com.IyfGZB.util.GoogleDriveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminSeminarController {

    public static final Logger logger = LoggerFactory.getLogger(AdminSeminarController.class);
@Autowired
private SeminarOperation seminarOperation;

@Autowired
private GoogleDriveService googleDriveService;

    @GetMapping(value = "/Seminars")
    public List<Seminar> getAllSeminar()
    {
        return seminarOperation.getAllSeminars();
    }

    @PostMapping(value = "/createseminar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createSeminar(@RequestPart("form") Seminar seminar, @RequestPart("file") MultipartFile multipartFile)
    {
        try{
            String fileUrl=googleDriveService.uploadFile(seminar,multipartFile);
            seminar.setThumbNailUrl(fileUrl);
            return new ResponseEntity<>(seminarOperation.createSeminar(seminar),HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO("danger",
                    "OOPS..!! Seminar Could Not Created Please Try Again"),HttpStatus.OK);

        }

    }

    @PutMapping(value = "/updateSeminar")
    public ResponseEntity<?> updateSeminarDetails(@RequestPart("form") Seminar seminar, @RequestPart("file") MultipartFile multipartFile){

        String fileUrl= null;
        try {
            fileUrl = googleDriveService.uploadFile(seminar,multipartFile);
            seminar.setThumbNailUrl(fileUrl);
            return new ResponseEntity<CommonResponseDTO>(seminarOperation.updateSeminar(seminar),HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Some error occured while updating seminar",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/seminar/{seminarId}")
    public SeminarRecordDTO getSeminar(@PathVariable("seminarId") Long seminarId){

        return seminarOperation.getSeminar(seminarId);

    }

//    @DeleteMapping("/seminar/{seminarId}")
//    public ResponseEntity<?> deleteSeminar(@PathVariable("seminarId") Long seminarId){
//
//    }




    @GetMapping("/drive")
    public void getDrive() throws GeneralSecurityException, IOException {

        googleDriveService.getDriveService();

    }



}
