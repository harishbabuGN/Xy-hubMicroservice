package com.example.XyhubEmployee.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.XyhubCommon.Component.CurrentUser;
import com.example.XyhubEmployee.Entity.EmployeePermission;
import com.example.XyhubEmployee.Repository.EmployeePermissionRepository;
//import com.example.XyhubEmployee.admin.model.CurrentUser;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class EmployeePermissionConfig {

	@Autowired
	CurrentUser currentUser;
	
	@Autowired
	EmployeePermissionRepository empPermissionRepo;
	
	@SuppressWarnings("unchecked")
	public boolean isHavingpersmission(String permission) throws Exception, IllegalAccessException, NoSuchFieldException, SecurityException {
		EmployeePermission ep = empPermissionRepo.getbyUserId(currentUser.getUserId());
		
		ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(ep, Map.class);
        if(map.containsKey(permission)) {
        	return (boolean) map.get(permission);
        }else {
        	return false;
        }
	
	}
}
