package com.IyfGZB.services;


import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.userdto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class UserRoleMappingService {


    @Autowired
    UserInfoRepository userInfoRepository;


    public static final Logger logger = LoggerFactory.getLogger(UserRoleMappingService.class);


    public List<UserDto> getAllUser(Integer userPerPage,Integer pageIndex){
        try{
            PageRequest pageRequest=new PageRequest(pageIndex,userPerPage,Sort.Direction.ASC,"username");

            Page<UserInfo> userInfos=userInfoRepository.findAll(pageRequest);
            int sNo=1;
           List<UserDto> userDtoList = new ArrayList<>();
           userInfos.forEach(userInfo -> {
               UserDto userDto=new UserDto();

               userDto.setUserId(userInfo.getId());
               userDto.setEmail(userInfo.getEmail());
               userDto.setMobileNumber(userInfo.getMobileNumber());
               Set<Role> roles=userInfo.getRoles();
               roles.forEach(role -> {
                   userDto.setRole(role.getRole());
               });
               userDto.setUserName(userInfo.getUsername());
               userDtoList.add(userDto);
           });
           return userDtoList;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }

}
