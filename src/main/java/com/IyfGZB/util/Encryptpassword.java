package com.IyfGZB.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryptpassword {


    public static String encryptPassword(String rawPassword)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(rawPassword);
    }
}
