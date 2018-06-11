package com.IyfGZB.securityservices;

import com.IyfGZB.domain.UserInfo;
import com.IyfGZB.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author kamal berriga
 *
 */
@Service
public class UserService {

	@Autowired
	UserInfoRepository userRepository;

	public UserInfo save(UserInfo user) {
		return userRepository.saveAndFlush(user);
	}

	public UserInfo update(UserInfo user) {
		return userRepository.save(user);
	}

	public UserInfo find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	public UserInfo find(Long id) {
		return userRepository.getOne(id);
	}
}
