package com.IyfGZB.services;

import com.IyfGZB.domain.Udgaar;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.UdgaarRepo;
import com.IyfGZB.securityservices.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UdgaarOperation {

    @Autowired
    UdgaarRepo udgaarRepo;
    public static final Logger logger = LoggerFactory.getLogger(UdgaarOperation.class);


    public String udgaarRegistration(String payMode){
        try{
            UserInfo userInfo=CurrentUser.getCurrentUser();
            Udgaar udgaar=new Udgaar();
            udgaar.setPayMode(payMode);
            udgaar.setUserInfo(userInfo);
            udgaarRepo.saveAndFlush(udgaar);
            return "User Registered Successfully For UDGAAR";

        }catch (Exception e){
            logger.error(e.getMessage());
            return "User Registration Failed For UDGAAR";
        }


    }

}
