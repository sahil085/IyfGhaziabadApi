package com.IyfGZB.repositories;


import com.IyfGZB.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

    Course findCourseById(Long id);
    List<Course> findAllByCreatedBy(String createdBy);

}
