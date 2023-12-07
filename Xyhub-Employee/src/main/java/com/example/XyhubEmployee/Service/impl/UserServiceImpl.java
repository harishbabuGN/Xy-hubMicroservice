package com.example.XyhubEmployee.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.XyhubEmployee.Repository.UserRepository;
import com.example.XyhubEmployee.Service.UserService;
import com.example.XyhubEmployee.admin.model.User;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public User loadbyusername(String username) {
		
		return userRepository.findByUsername(username);
//				.loadUserByUsername(username);
	}


//	@Override
//	public User adduserdetails(User user) {
//		
//		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//		return userRepository.save(user);
//	}
}
