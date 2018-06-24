package com.IyfGZB.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage()
    {

        return "home";
    }

    @GetMapping("/register")
    @PreAuthorize("permitAll()")
    public String register()
    {
        return "registration";
    }

//    @GetMapping("/login")
//    public String login()
//    {
//        return ""
//    }
}
