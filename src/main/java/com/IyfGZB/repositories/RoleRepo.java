package com.IyfGZB.repositories;

import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sahil on 29/7/18.
 */

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {



    Role findByRole(String role);

}
