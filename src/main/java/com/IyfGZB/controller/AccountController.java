package com.IyfGZB.controller;

import com.IyfGZB.constants.RoleConstant;
import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.securityservices.UserService;
import com.IyfGZB.services.UserAccountService;
import com.IyfGZB.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.naming.Binding;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author kamal berriga
 *
 */
@RestController
@RequestMapping("account")
public class AccountController {


    public static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserAccountService userAccountService;

    // request method to create a new account by a guest
    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserInfo newUser) {

        Role role=new Role();
        role.setRole(RoleConstant.ROLE_USER);
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        newUser.setRoles(roles);
//        newUser.setPassword(encode);
        return new ResponseEntity<String>(userAccountService.createUser(newUser), HttpStatus.CREATED);
    }

    // this is the login api/service
    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        logger.info("user logged "+principal);
        return principal;
    }

    @CrossOrigin
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user")
    public UserInfo getUser(){

        UserInfo user=new UserInfo();
        user.setUsername("hare krishna");
        return user;
    }
    @CrossOrigin
    @PostMapping("/logout")
    public String logout()
    {
        SecurityContextHolder.getContext().setAuthentication(null);
        logger.info("------------------- logout");
        return "logout";
    }

    @CrossOrigin
    @GetMapping("/check")
    public void pass()
    {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encode=passwordEncoder.encode("123");

        System.out.println(" ----  "+ passwordEncoder.matches("123",encode));
    }




}
