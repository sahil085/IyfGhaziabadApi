package com.IyfGZB.repositories;

import com.IyfGZB.domain.CallingSewa;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sahil on 23/9/18.
 */
@Repository
public interface CallingSewaRepo extends JpaRepository<CallingSewa,Long> {


    Page<CallingSewa> findAllByVolunteerAndSeminar(UserInfo volunteer, Seminar seminar, Pageable pageable);

}
