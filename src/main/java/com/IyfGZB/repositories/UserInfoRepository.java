package com.IyfGZB.repositories;
import com.IyfGZB.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kamal berriga
 *
 */
/* this the user  Repository interface  */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

      UserInfo findOneByUsername(String username);
      UserInfo findByEmail(String username);

      @Query(value = "select email from User_Info",nativeQuery = true)
      List<String> getAllEmails();

    @Override
    UserInfo getOne(Long aLong);
}
