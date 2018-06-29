package com.IyfGZB.securityservices;

import com.IyfGZB.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class CurrentUser {


    public static UserInfo getCurrentUser()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        UserInfo userInfo= (UserInfo)authentication.getPrincipal();

        return userInfo;
    }


}
