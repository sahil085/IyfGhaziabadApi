package com.IyfGZB.controller;

import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.roleconstant.RolesConstant;
import com.IyfGZB.securityservices.UserService;
import com.IyfGZB.services.UserAccountService;
import com.IyfGZB.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kamal berriga
 *
 */
@Controller
@RequestMapping("account")
public class AccountController {


	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
    UserAccountService userAccountService;

	@PostMapping("/register")
    @PreAuthorize("permitAll()")
    public String registrationProcess(@ModelAttribute UserInfo userInfo, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes,
                                      Model model)
    {
        if(bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach( e -> logger.error(e.getDefaultMessage()));
            redirectAttributes.addAttribute("errMessage","Please Try Again After Some Time");
            return "redirect:/register";

        }else {
            String msg= userAccountService.createUser(userInfo);
            if(msg.equals("Please Try Again Registration unsuccessfull"))
            {
                redirectAttributes.addAttribute("errMessage",msg);
              return "redirect:/register";
            }else {
                redirectAttributes.addAttribute("message",msg);
                return "redirect:/home";
            }
        }
    }

//    @PostMapping("/login")
//    @PreAuthorize("permitAll()")
//    public void loginProcess(Model model){
//
//    }


}
