package com.IyfGZB.controller;

import com.IyfGZB.dto.UserListForAttendanceDTO;
import com.IyfGZB.services.UserInfoOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sahil on 5/8/18.
 */
@RestController
@CrossOrigin
public class UserInfoController {

    public static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoOperation userInfoOperation;

    @GetMapping("/usersForSeminarAttendance/{input}")
    public List<UserListForAttendanceDTO> getAllUserBasicInfo(@PathVariable("input") String input){
        System.out.println(" --- "+input);
        return userInfoOperation.getAllUserBasicInfo(input);
    }

}
