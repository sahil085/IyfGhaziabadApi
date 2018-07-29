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

            Udgaar udgaar=  udgaarRepo.findByUserInfo(userInfo);
            if(udgaar == null) {
                Udgaar udgaar1 = new Udgaar();
                udgaar1.setPayMode(payMode);
                udgaar1.setUserInfo(userInfo);
                udgaarRepo.saveAndFlush(udgaar1);
                return "User Registered Successfully For UDGAAR : " + payMode;
            }else{
                udgaar.setPayMode(payMode);
                udgaarRepo.saveAndFlush(udgaar);
                return "Paymode of the user changed to "+payMode;
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            return "User Registration Failed For UDGAAR";
        }


    }

}
