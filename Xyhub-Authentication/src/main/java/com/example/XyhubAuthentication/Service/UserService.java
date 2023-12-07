//package com.example.XyhubAuthentication.Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.example.XyhubAuthentication.Repo.UserRepo;
//import com.example.XyhubAuthentication.model.XyhubUserDetails;
//
//
//@Service
//public class UserService {
//	
//	@Autowired
//	UserRepo userRepo;
//	
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//
//	public XyhubUserDetails addUserdetails(XyhubUserDetails user) {
//		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//		return userRepo.save(user);
//	}
//
//	public XyhubUserDetails loadusernamebyusername(String username) {
//		
//		return userRepo.loadUserByUsername(username);
//	}
//
//}
