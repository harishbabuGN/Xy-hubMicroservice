package com.example.XyhubEmployee.Service;

import org.springframework.stereotype.Service;

import com.example.XyhubEmployee.admin.model.User;


@Service
public interface UserService  {

	User loadbyusername(String username);

//	User adduserdetails(User user);
	
}
