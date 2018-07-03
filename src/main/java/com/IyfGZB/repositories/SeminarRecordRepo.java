package com.IyfGZB.repositories;

import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.SeminarRecord;
import com.IyfGZB.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeminarRecordRepo extends JpaRepository<SeminarRecord,Long> {

      SeminarRecord findSeminarRecordBySeminarAndUser(Seminar seminar, UserInfo userInfo);

      SeminarRecord findSeminarRecordById(Long id);
}
