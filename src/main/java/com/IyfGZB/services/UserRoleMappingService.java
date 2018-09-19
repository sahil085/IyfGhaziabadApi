package com.IyfGZB.services;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.co.UserCo;
import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.User;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.RoleRepo;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.userdto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserRoleMappingService {


    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    RoleRepo roleRepo;

    public static final Logger logger = LoggerFactory.getLogger(UserRoleMappingService.class);


    public Map<String,Object> getAllUser(Integer userPerPage,Integer pageIndex){
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
               userDto.setClassLevel(userInfo.getClassLevel());
               Set<Role> roles=userInfo.getRoles();
               roles.forEach(role -> {
                   userDto.setCurrentRole(role.getRole());
               });
               userDto.setUserName(userInfo.getUsername());
               userDtoList.add(userDto);
           });
           Long totalUsers = userInfoRepository.count()/userPerPage;
           Map<String,Object> map = new HashMap<>();
           map.put("userList",userDtoList);
           map.put("totalPage",totalUsers);
           return map;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }



    public  CommonResponseDTO changeUserRole(UserCo userCo){

        try{
            Optional<UserInfo> userInfo1 = userInfoRepository.findById(userCo.getUserId());
            if(userInfo1.isPresent()){
                UserInfo userInfo = userInfo1.get();

                Set<Role> roles = new HashSet<>();
                Role role = roleRepo.findByRole(userCo.getRole());
                if(role != null ){
                    role.setRole(userCo.getRole());
                    roles.add(role);
                }else{
                    role = new Role();
                    role.setRole(userCo.getRole());
                    roles.add(role);
                }


                userInfo.setRoles(roles);
                userInfo.setClassLevel(userCo.getClassLevel());

                userInfoRepository.save(userInfo);
            }
            return new CommonResponseDTO("success","Role Changed Successfully");
        }catch (Exception e){
            e.printStackTrace();

            logger.error(e.getMessage());
            return new CommonResponseDTO("danger","Please try again after sometime");

        }


    }

}
