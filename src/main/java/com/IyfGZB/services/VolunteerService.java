package com.IyfGZB.services;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.controller.AdminCourseController;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public static final Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    public CommonResponseDTO registrationService(UserInfo userInfo){

        // check if user already exists with that email id
        try{
            UserInfo userInfo1=userInfoRepository.findByEmail(userInfo.getEmail());
            if(userInfo1==null){
                return new CommonResponseDTO("danger","User Already Exists With That Email Id");
            }else{
                userInfoRepository.save(userInfo);
                return new CommonResponseDTO("success","User Registered Successfully");
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","User Registration Failed");
        }

    }
}
