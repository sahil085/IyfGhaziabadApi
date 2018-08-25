package com.IyfGZB.services;

import com.IyfGZB.domain.Seminar;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.dto.UserListForAttendanceDTO;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.userdto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoOperation {

    public static final Logger logger = LoggerFactory.getLogger(UserInfoOperation.class);


    @Autowired
        private UserInfoRepository userInfoRepository;

    public List<String> getallEmails(String classLevel,String city){

      List<UserInfo> list =userInfoRepository.findAllByClassLevelAndCity(classLevel,city);

      return list.stream().map(UserInfo::getEmail).collect(Collectors.toList());

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
}
