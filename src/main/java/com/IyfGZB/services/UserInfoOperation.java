package com.IyfGZB.services;

import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.dto.UserListForAttendanceDTO;
import com.IyfGZB.dto.UserProfileDTO;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.securityservices.CurrentUser;
import com.IyfGZB.userdto.UserDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoOperation {

    public static final Logger logger = LoggerFactory.getLogger(UserInfoOperation.class);

    @Autowired
    public ModelMapper modelMapper;


    @Autowired
        private UserInfoRepository userInfoRepository;

    public List<String> getallEmails(){

       return userInfoRepository.getAllEmails();

    }

    public List<UserListForAttendanceDTO> getAllUserBasicInfo(String input){

        try{
            List<UserInfo> userInfos = userInfoRepository.findAllByUsernameContainingOrEmailContaining(input,
                    input);
            List<UserListForAttendanceDTO> users= new ArrayList<>();
            userInfos.forEach( userInfo -> {
               UserListForAttendanceDTO userListForAttendanceDTO = new UserListForAttendanceDTO();
                userListForAttendanceDTO.setMobileNumber(userInfo.getMobileNumber());
                userListForAttendanceDTO.setUserName(userInfo.getUsername());
                userListForAttendanceDTO.setUserEmail(userInfo.getEmail());
                userListForAttendanceDTO.setUserId(userInfo.getId());
                users.add(userListForAttendanceDTO);
            });
            return users;

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public UserProfileDTO getUserDetails(){
        UserInfo userInfo = CurrentUser.getCurrentUser();
      UserProfileDTO userProfileDTO = modelMapper.map(userInfo,UserProfileDTO.class);

        System.out.println(userProfileDTO);
        return userProfileDTO;

    }
}
