package com.IyfGZB.repositories;

import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarAttendance;
import com.IyfGZB.domain.User;
import com.IyfGZB.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sahil on 3/8/18.
 */
@Repository
public interface SeminarAttendanceRepo extends JpaRepository<SeminarAttendance,Long> {


    List<SeminarAttendance> findAllBySeminar(Seminar seminar);
    SeminarAttendance findSeminarAttendanceBySeminarAndUserId(Seminar seminar, Long userId);


}
