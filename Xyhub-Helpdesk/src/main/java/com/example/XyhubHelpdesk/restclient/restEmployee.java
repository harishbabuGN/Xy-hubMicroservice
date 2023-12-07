package com.example.XyhubHelpdesk.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.XyhubHelpdesk.constants.EmployeeClient;

@Component
@FeignClient(name = "xyhub-Employee")
public interface restEmployee {
	
	@GetMapping("/isEmployeeActive/{userId}")
	public EmployeeClient isEmployeeActive(@PathVariable String userId);
	
	@GetMapping("/Employee/{userId}")
	public EmployeeClient getbyUserId(@PathVariable String userId);
}
