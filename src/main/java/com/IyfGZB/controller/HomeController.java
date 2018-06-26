package com.IyfGZB.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/login.html")
    @PreAuthorize("permitAll()")
    public String login()
    {
        return "login";
    }

    @ResponseBody
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public String loginProcessURL()
    {
        return "login successful";
    }
}
