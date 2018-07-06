package com.IyfGZB.services;

import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunService {

    @Autowired
    private UserInfoRepository userInfoRepository;


    public CommonResponseDTO registrationService(UserInfo userInfo){

        // check if user already exists with that email id
        try{

            UserInfo userInfo1=userInfoRepository.findByEmail(userInfo.getEmail());
            if(userInfo1==null){
                return new CommonResponseDTO("danger","User Already Exists With That Email Id");
            }else{
                userInfoRepository.save(userInfo);
                return new CommonResponseDTO("success","User Registered Successfully")_;
            }

        }catch (Exception e){

        }




    }
}
