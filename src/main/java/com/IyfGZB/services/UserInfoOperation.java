package com.IyfGZB.services;

import com.IyfGZB.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoOperation {

        @Autowired
        private UserInfoRepository userInfoRepository;

    public List<String> getallEmails(){

       return userInfoRepository.getAllEmails();

    }
}
