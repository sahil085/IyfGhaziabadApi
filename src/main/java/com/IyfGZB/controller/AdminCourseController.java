package com.IyfGZB.controller;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.constants.RoleConstant;
import com.IyfGZB.domain.Course;
import com.IyfGZB.services.CourseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("admin")
@PreAuthorize("hasAnyAuthority('USER')")
public class AdminCourseController {

    public static final Logger logger = LoggerFactory.getLogger(AdminCourseController.class);
    @Autowired
    private RoleConstant roleConstant;

    @Autowired
    private CourseOperation courseOperation;

    @CrossOrigin
    @PostMapping(value = "/createcourse",consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponseDTO createCourse(@RequestBody Course course)
    {
        try
        {

           return courseOperation.create(course);

        }catch (Exception e)
        {
            logger.error(" error in create course "+e.getMessage());
            return null;
//            return new ResponseEntity<>(new CommonResponseDTO("danger","Course Could Not Be Created Please Try Again"),HttpStatus.OK);
        }

    }
    @CrossOrigin
    @GetMapping("/courselist/{createdBy}")
    public ResponseEntity<?> getAllCourses(@PathVariable("createdBy") String createdBy)
    {
        try{
            return new ResponseEntity<>(courseOperation.getAllCourse(createdBy),HttpStatus.OK);
        }catch (Exception e)
        {
            logger.error(e.getMessage());
            return new ResponseEntity<String>("No Courses Yet",HttpStatus.OK);
        }
    }
    @CrossOrigin
    @DeleteMapping("/deletecourse/{courseid}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseid") Long courseId)
    {
       try{
          return new ResponseEntity<CommonResponseDTO>(courseOperation.delete(courseId),HttpStatus.OK);
       }catch (Exception e)
       {
           logger.error(" admin course controller "+e.getMessage());
           return new ResponseEntity<CommonResponseDTO>(new CommonResponseDTO("danger","Course Could Not Be Deleted Please Try Again"),HttpStatus.OK);
       }
    }
    @CrossOrigin
    @PutMapping("/updatecourse/{courseid}/{createdby}")
    public ResponseEntity<?> updateCourse(@PathVariable("courseid") Long courseId
                                            ,@RequestBody Course course)
    {
        try{
            return new ResponseEntity<>(courseOperation.update(course,courseId),HttpStatus.OK);

        }catch (Exception e)
        {
            logger.error(" admin course controller "+e.getMessage());
            return new ResponseEntity<>(new CommonResponseDTO("danger"," OOPS..!! Course Details Could Not Updated"),HttpStatus.OK);
        }
    }


}
