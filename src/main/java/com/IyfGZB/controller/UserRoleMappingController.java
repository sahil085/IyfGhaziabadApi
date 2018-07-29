package com.IyfGZB.controller;


import com.IyfGZB.CourseDTO.CommonResponseDTO;
import com.IyfGZB.co.UserCo;
import com.IyfGZB.domain.User;
import com.IyfGZB.services.UserRoleMappingService;
import com.IyfGZB.userdto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserRoleMappingController {

    @Autowired
    UserRoleMappingService userRoleMappingService;

    public static final Logger logger = LoggerFactory.getLogger(UserRoleMappingController.class);

    @GetMapping("/getAllUser/{userPerPage}/{pageNumber}")
    public Map<String,Object> getAllUser(@PathVariable("userPerPage") Integer userPerPage,
                                         @PathVariable("pageNumber") Integer pageNumber){
        return userRoleMappingService.getAllUser(userPerPage,pageNumber);
    }

    @PutMapping("/changeUserRole")
    public CommonResponseDTO changeuserRole(@RequestBody UserCo userCo){

        return userRoleMappingService.changeUserRole(userCo);


    }



}
