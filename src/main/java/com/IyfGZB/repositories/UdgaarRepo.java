package com.IyfGZB.repositories;

import com.IyfGZB.domain.Udgaar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UdgaarRepo extends JpaRepository<Udgaar,Long> {


}
