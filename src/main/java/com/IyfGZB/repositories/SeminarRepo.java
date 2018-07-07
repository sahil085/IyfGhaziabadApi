package com.IyfGZB.repositories;

import com.IyfGZB.domain.Seminar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SeminarRepo extends JpaRepository<Seminar,Long> {

    List<Seminar> findAllByDateAfter(Date date, Pageable pageable);
    Seminar findSeminarById(Long id);


}
