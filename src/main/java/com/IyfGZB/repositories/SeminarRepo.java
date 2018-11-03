package com.IyfGZB.repositories;

import com.IyfGZB.domain.Seminar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface SeminarRepo extends JpaRepository<Seminar,Long> {

    List<Seminar> findAllByDateAfter(Date date, Pageable pageable);
    List<Seminar> findAllByDateAfter(Date date);
    List<Seminar> findAllByDate(@Temporal(TemporalType.DATE) Date date);
    List<Seminar> findAllByDateBetweenAndCategory(Date lastTwoMonthDate, Date currentDate, String category);
    Seminar findSeminarById(Long id);
    List<Seminar> findAllByCategory(String category);



}
