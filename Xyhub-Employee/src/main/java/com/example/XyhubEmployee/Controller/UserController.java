package com.example.XyhubEmployee.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.XyhubEmployee.Service.UserService;
import com.example.XyhubEmployee.admin.model.User;



@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/createuser/{username}")
	public User loadUserByUsername(@PathVariable String username) {
		return userService.loadbyusername(username);
		
	}
//	@PostMapping("/adduser")
//	public User addUser(@RequestBody User user) {
//		return userService.adduserdetails(user);
//	}

}
