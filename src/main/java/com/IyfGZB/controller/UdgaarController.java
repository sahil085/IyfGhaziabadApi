package com.IyfGZB.controller;

import com.IyfGZB.services.UdgaarOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UdgaarController {

    @Autowired
    UdgaarOperation udgaarOperation;

    @PostMapping("/udgaarRegistration")
    public String udgaarRegistration(@RequestBody String payMode){
       return udgaarOperation.udgaarRegistration(payMode);
    }


}
