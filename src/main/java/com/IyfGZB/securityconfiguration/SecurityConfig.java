package com.IyfGZB.securityconfiguration;

import com.IyfGZB.constants.ClassLevel;
import com.IyfGZB.constants.RoleConstant;
import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.filters.CorsFilter;
import com.IyfGZB.repositories.UserInfoRepository;
import com.IyfGZB.securityservices.CustomUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
     CustomUserDetailsService appUserDetailsService;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");

            }
        };
    }

    @PostConstruct
    public void createAdmin(){
        UserInfo userInfo1 = userInfoRepository.findByEmail("vermasahil269@gmail.com");
        if(userInfo1==null){
            UserInfo userInfo = new UserInfo();
            userInfo.setClassLevel(ClassLevel.One_Time_Program);
            userInfo.setPermanentAddress("permanent addrr");
            userInfo.setCurrentAddress("Current adddrr");
            userInfo.setMobileNumber(8920041231L);
            userInfo.setGender("Male");
            userInfo.setEmail("vermasahil269@gmail.com");
            userInfo.setUsername("Sahil verma");
            userInfo.setRoundsChant(8);
            userInfo.setCounslerName("HG Sunder Gopal das");
            userInfo.setFacilitatorName("HG Charu Govind Das");
            userInfo.setSeniorFacilitatorName("HG Sarv Mangal Gaur Das");
            userInfo.setCity("Ghaziabad");
            userInfo.setBrahmchari(false);
            userInfo.setPassword(new BCryptPasswordEncoder().encode("123"));
            userInfo.setState("Uttar Pradesh");
            Set<Role> roles = new HashSet<>();
            Role role = new Role();
            role.setRole(RoleConstant.ROLE_ADMIN);
            roles.add(role);
            userInfo.setRoles(roles);
            userInfoRepository.saveAndFlush(userInfo);

            System.out.println(" ****  Admin Created  ****");
        }


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                // starts authorizing configurations
                .authorizeRequests()
                // ignoring the guest's urls "
                .antMatchers("/account/register","/account/login","/logout","/account/check","/admin/drive","/sheet").permitAll()
                // authenticate all remaining URLS
                .anyRequest().authenticated().and()
                /* "/logout" will log the user out by invalidating the HTTP Session,
                 * cleaning up any {link rememberMe()} authentication that was configured, */
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .logoutUrl("/account/logout")
                .logoutSuccessUrl("/account/login")
                .and()
                // enabling the basic authentication
                .httpBasic().and()
                // configuring the session on the server
                // disabling the CSRF - Cross Site Request Forgery
                .csrf().disable();
    }


}
