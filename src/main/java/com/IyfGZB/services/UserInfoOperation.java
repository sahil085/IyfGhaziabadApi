package com.IyfGZB.services;

import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.dto.UserListDTO;
import com.IyfGZB.dto.UserProfileEditDTO;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.securityservices.CurrentUser;
import com.IyfGZB.userdto.UserDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<UserListDTO> getAllUserBasicInfo(String input){

        try{
            List<UserInfo> userInfos = userInfoRepository.findAllByUsernameContainingOrEmailContaining(input,
                    input);
            List<UserListDTO> users= new ArrayList<>();
            userInfos.forEach( userInfo -> {
               UserListDTO userListDTO = new UserListDTO();
                userListDTO.setMobileNumber(userInfo.getMobileNumber());
                userListDTO.setUserName(userInfo.getUsername());
                userListDTO.setUserEmail(userInfo.getEmail());
                userListDTO.setUserId(userInfo.getId());
                users.add(userListDTO);
            });
            return users;

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public UserProfileEditDTO getUserDetails(Long userId){
        try{
            UserInfo userInfo = userInfoRepository.findById(userId).get();
            UserProfileEditDTO userProfileEditDTO = modelMapper.map(userInfo,UserProfileEditDTO.class);
            return userProfileEditDTO;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }


    }

    public UserProfileEditDTO updateUserprofile(UserProfileEditDTO userProfileEditDTO){
       try{
           UserInfo userInfo = userInfoRepository.findByEmail(userProfileEditDTO.getEmail());
           userInfo = userInfo.updateUser(userProfileEditDTO,userInfo);
           userInfoRepository.saveAndFlush(userInfo);
           userProfileEditDTO = modelMapper.map(userInfo, UserProfileEditDTO.class);
           return userProfileEditDTO;
       }catch (Exception e){
           logger.error(e.getMessage());
           return null;
       }
    }

    public Map<String,Object> getAllUser(Integer userPerPage, Integer pageIndex){
        try{
            PageRequest pageRequest=new PageRequest(pageIndex,userPerPage, Sort.Direction.ASC,"username");

            Page<UserInfo> userInfos=userInfoRepository.findAll(pageRequest);
            List<UserListDTO> userDtoList = new ArrayList<>();
            userInfos.forEach(userInfo -> {
                UserListDTO userDto = new UserListDTO();
                userDto.setUserId(userInfo.getId());
                userDto.setUserEmail(userInfo.getEmail());
                userDto.setUserName(userInfo.getUsername());
                userDto.setMobileNumber(userInfo.getMobileNumber());
                userDtoList.add(userDto);
            });
            Map<String,Object> map = new HashMap<>();
            map.put("userList",userDtoList);
            map.put("totalPage",userInfos.getTotalPages());
            return map;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }

}
