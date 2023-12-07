package com.example.XyhubCommon.Component;

import org.springframework.stereotype.Component;


@Component
public class CurrentUser {

	private String name;

	private String userId;

	private String userRole;

	private String scopeId;

	private String firstName;
	
private String IpAddress;



//	private EmployeePermission empPermission;
//
//	public EmployeePermission getPermission() {
//		return empPermission;
//	}
//
//	public void setPermission(EmployeePermission permission) {
//		this.empPermission = permission;
//	}

	public String getIpAddress() {
	return IpAddress;
}

public void setIpAddress(String ipAddress) {
	IpAddress = ipAddress;
}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getScopeId() {
		return scopeId;
	}

	public void setScopeId(String scopeId) {
		this.scopeId = scopeId;
	}

}
