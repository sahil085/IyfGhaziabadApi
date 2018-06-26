package com.IyfGZB.securityservices;

import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * This Service class for providing the user credentials from the database.
 *
 * @author kamal berriga
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserInfoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user=  null;
        try {
            user = userRepository.findByEmail(username);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        if(user == null){
            throw new UsernameNotFoundException("User does not exists");
        }
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        Set<Role> roleSet=user.getRoles();
        roleSet.forEach(role ->
                auths.add(new SimpleGrantedAuthority(role.getRole())
        )
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                auths
        );
    }

}
