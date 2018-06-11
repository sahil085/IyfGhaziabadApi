package com.IyfGZB.controller;

import com.IyfGZB.domain.Role;
import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.roleconstant.RolesConstant;
import com.IyfGZB.securityservices.UserService;
import com.IyfGZB.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
	private UserService userService;

	// request method to create a new account by a guest
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.PUT)
	public ResponseEntity<?> createUser(@RequestBody UserInfo newUser) {
		if (userService.find(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity(
					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
					HttpStatus.CONFLICT);
		}
		Role role=new Role();
		role.setRole(RolesConstant.USER);
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		newUser.setRole(roles);
		newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
		return new ResponseEntity<UserInfo>(userService.save(newUser), HttpStatus.CREATED);
	}

	// this is the login api/service
	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
//		User user=userService.find(principal.getName());
//		Authentication auth =
//				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//
//		SecurityContextHolder.getContext().setAuthentication(auth);

		logger.info("user logged "+principal);
		return principal;
	}

	@CrossOrigin
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/user")
	public UserInfo getUser(){

		UserInfo user=new UserInfo();
		user.setUsername("hare krishna");
		user.setFullName("radhe shyam hari guru");
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

	
	
}
