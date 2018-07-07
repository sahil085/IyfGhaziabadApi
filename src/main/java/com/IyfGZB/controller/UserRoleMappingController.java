package com.IyfGZB.controller;


import com.IyfGZB.services.UserRoleMappingService;
import com.IyfGZB.userdto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserRoleMappingController {

    @Autowired
    UserRoleMappingService userRoleMappingService;

    public static final Logger logger = LoggerFactory.getLogger(UserRoleMappingController.class);

    @GetMapping("/getAllUser/{userPerPage}/{pageNumber}")
    public List<UserDto> getAllUser(@PathVariable("userPerPage") Integer userPerPage,
                                    @PathVariable("pageNumber") Integer pageNumber){
        return userRoleMappingService.getAllUser(userPerPage,pageNumber);
    }



}
