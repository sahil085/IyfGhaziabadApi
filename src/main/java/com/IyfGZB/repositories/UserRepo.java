package com.IyfGZB.repositories;

import com.IyfGZB.domain.User;
import com.IyfGZB.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    UserInfo findOneByUsername(String username);
}
