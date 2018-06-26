package com.IyfGZB.controller;

import com.IyfGZB.constants.RoleConstant;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class DashboardController {

    @ResponseBody
    @GetMapping("/dashboard")
    @Secured(RoleConstant.ROLE_USER)
    public String dashboardIndex(Model model){
        return "login Success";
    }

}
