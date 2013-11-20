package com.twistlet.kiptanui.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twistlet.kiptanui.model.entity.User;
import com.twistlet.kiptanui.model.repository.UserRepository;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	private UserRepository userRepository;

	@Autowired
	public AdminUserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listUser() {
		return userRepository.findAll();
	}

}
