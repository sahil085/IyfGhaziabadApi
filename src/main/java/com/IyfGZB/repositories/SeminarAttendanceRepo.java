package com.IyfGZB.repositories;

import com.IyfGZB.domain.SeminarAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sahil on 3/8/18.
 */
@Repository
public interface SeminarAttendanceRepo extends JpaRepository<SeminarAttendance,Long> {


}
