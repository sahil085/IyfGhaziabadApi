package com.IyfGZB.services;

import com.IyfGZB.constants.RoleConstant;
import com.IyfGZB.constants.VedicLevel;
import com.IyfGZB.controller.AccountController;
import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.util.Encryptpassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
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
            userInfo.setVedicLevel(VedicLevel.VEDIC_LEVEL_ONE);
            userInfo.setBrahmchari(false);
            userInfo.setPassword(Encryptpassword.encryptPassword(userInfo.getPassword()));
            userInfoRepository.save(userInfo);
            return "Registered Successfully";
        }catch (Exception e)
        {
            System.out.println(e);
            return " Please Try Again Registration unsuccessfull ";
        }
    }


}