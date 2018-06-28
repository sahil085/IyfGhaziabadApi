//package com.IyfGZB.securityservices;
//
//import com.IyfGZB.domain.UserInfo;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Set;
//
//public class CustomUserDetail implements UserDetails {
//
//    private UserInfo user;
//
//    Set<GrantedAuthority> authorities=null;
//
//
//
//    public UserInfo getUser() {
//        return user;
//    }
//
//    public void setUser(UserInfo user) {
//        this.user = user;
//    }
//
//    @Override
//    public Set<GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}