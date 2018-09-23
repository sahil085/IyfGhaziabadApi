package com.IyfGZB.repositories;
import com.IyfGZB.constants.RoleConstant;
import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.UserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

      UserInfo findOneByUsername(String username);
      UserInfo findByEmail(String username);
      List<UserInfo> findAllByUsernameContainingOrEmailContaining(String username,String email);

      List<UserInfo> findAllByClassLevelAndCity(String classLevel, String city);
      List<UserInfo> findAllByCity(String city);


    @Override
    UserInfo getOne(Long Long);
}
