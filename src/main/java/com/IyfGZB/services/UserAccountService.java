package com.IyfGZB.services;

import com.IyfGZB.constants.ClassLevel;
import com.IyfGZB.constants.RoleConstant;
import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.util.Encryptpassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserAccountService {

    public static final Logger logger = LoggerFactory.getLogger(UserAccountService.class);


    @Autowired
    UserInfoRepository userInfoRepository;

    public String createUser(UserInfo userInfo) {
        try {
            Role role = new Role();
            role.setRole(RoleConstant.ROLE_USER);
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(role);
            userInfo.setRoles(roleSet);
            userInfo.setClassLevel(ClassLevel.One_Time_Program);
            userInfo.setBrahmchari(false);
            userInfo.setPassword(Encryptpassword.encryptPassword(userInfo.getPassword()));
            if (userInfo.getRoundsChant() == null) {
                userInfo.setRoundsChant(0);
            }
            userInfoRepository.saveAndFlush(userInfo);
            return "Registered Successfully";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return " Please Try Again Registration unsuccessfull ";
        }
    }

}