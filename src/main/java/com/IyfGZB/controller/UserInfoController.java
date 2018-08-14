package com.IyfGZB.controller;

import com.IyfGZB.dto.UserListDTO;
import com.IyfGZB.dto.UserProfileEditDTO;
import com.IyfGZB.services.UserInfoOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by sahil on 5/8/18.
 */
@RestController
@CrossOrigin
public class UserInfoController {

    public static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoOperation userInfoOperation;

    @PreAuthorize("hasAnyAuthority('ADMIN','IYFVolunteer')")
    @GetMapping("/usersForSeminarAttendance/{input}")
    public List<UserListDTO> getAllUserBasicInfo(@PathVariable("input") String input){
        System.out.println(" --- "+input);
        return userInfoOperation.getAllUserBasicInfo(input);
    }

    @GetMapping("/user/{userId}")
    public UserProfileEditDTO getUserProfileDetails(@PathVariable("userId") Long userId){

        return userInfoOperation.getUserDetails(userId);

    }
    @GetMapping("/users/{userPerPage}/{pageNumber}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Map<String,Object> getAllUser(@PathVariable("userPerPage") Integer userPerPage,
                                         @PathVariable("pageNumber") Integer pageNumber){
        return userInfoOperation.getAllUser(userPerPage,pageNumber);
    }



    @PutMapping("/editProfile")
    public UserProfileEditDTO updateProfileDetails(@RequestBody UserProfileEditDTO userProfileEditDTO){
       return userInfoOperation.updateUserprofile(userProfileEditDTO);
    }

}
