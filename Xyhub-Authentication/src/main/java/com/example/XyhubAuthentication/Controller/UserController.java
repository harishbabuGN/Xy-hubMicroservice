//package com.example.XyhubAuthentication.Controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.XyhubAuthentication.Service.UserService;
//import com.example.XyhubAuthentication.model.XyhubUserDetails;
//
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//
//@RestController
//public class UserController {
//	
//	@Autowired
//	UserService userService;
//	
//	
//	@PostMapping("/addUser")
//	public XyhubUserDetails addUser(@RequestBody XyhubUserDetails user) {
//		
//		return userService.addUserdetails(user);
//	}
//	@GetMapping("/getusername/{username}")
//	public XyhubUserDetails loadUserByUsername(@PathVariable String username) {
//		
//		return userService.loadusernamebyusername(username);
//		
//	}
//
//}
