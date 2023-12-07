package com.example.XyhubAuthentication.restclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.XyhubCommon.constants.XyhubUserDetails;

@Component
@FeignClient(name = "xyhub-Employee")
public interface XyHubUserClient {

//	@GetMapping("/createuser/{username}")
//	public XyhubUserDetails loadUserByUsername(@PathVariable String username);
	
	@GetMapping("/createuser/{username}")
	public XyhubUserDetails loadUserByUsername(@PathVariable String username);
	
}
