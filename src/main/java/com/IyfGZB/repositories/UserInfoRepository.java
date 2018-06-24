package com.IyfGZB.repositories;
import com.IyfGZB.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kamal berriga
 *
 */
/* this the user  Repository interface  */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

      UserInfo findOneByUsername(String username);
      UserInfo findByUsername(String username);

    @Override
    UserInfo getOne(Long aLong);
}
