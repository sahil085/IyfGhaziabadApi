package com.IyfGZB.services;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.Course;
import com.IyfGZB.repositories.CourseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseOperation {
    public static final Logger logger = LoggerFactory.getLogger(CourseOperation.class);

    @Autowired
    private CourseRepo courseRepo;

    public CommonResponseDTO create(Course course)
    {
        try {
            courseRepo.save(course);
            return new CommonResponseDTO("success", "Course Created Successfully");
        }catch (Exception e)
        {
            logger.error(" error in create course service "+e);
            return new CommonResponseDTO("danger", "OOPS..!! Course Could Not Be Create Please Try Again");
        }
    }

    public CommonResponseDTO delete(Long courseId)
    {
        try{

          Course course  =  courseRepo.findCourseById(courseId);
          courseRepo.delete(course);
          return new CommonResponseDTO("success","Course Deleted Successfully");

        }catch (Exception e)
        {
            logger.error(" error in "+e.getMessage());
            return new CommonResponseDTO("danger", "OOPS..! Course Could Not Be Deleted");
        }
    }

    public List<Course> getAllCourse(String createdBy) {

        try{
            return null;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public CommonResponseDTO update(Course course,Long courseId)
    {
        try{
            Course course1=courseRepo.findCourseById(courseId);
            course1.setCourseType(course.getCourseType());
            course1.setDescription(course.getDescription());
            course1.setDuration(course.getDuration());
            course1.setGender(course.getGender());
            course1.setTittle(course.getTittle());
            course1.setVedicLevel(course.getVedicLevel());
            courseRepo.save(course1);
            return new CommonResponseDTO("success"," Course Details Update Successfully");
        }catch (Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger"," OOPS..!! Course Details Could Not Updated");
        }
    }
}
