package com.example.XyhubCommon.constants;

import java.io.Serializable;
import java.util.Date;

import com.example.XyhubCommon.enumType.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class XyhubUserDetails implements Serializable  {
	

	@JsonProperty("user_id")
	private String id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;
	
	@JsonProperty("userRole")
	private String userRole;

	@JsonProperty("createdAt")
	private Date createdAt;

	@JsonProperty("uid")
	private String uid;
	
	
	@JsonProperty("name")
	private String name;

	@JsonProperty("status")
	private String status;

	@JsonProperty("accesskey")
	private UserStatus accesskey;

	@JsonProperty("permission")
	private Integer permission;

	@JsonProperty("resetPasswordToken")
	private String resetPasswordToken;
	
	@JsonProperty("scopeId")
	private String scopeId;
	
	@JsonProperty("isSuperAdmin")
	private boolean isSuperAdmin;
	

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("osType")
	private String osType;
	
	@JsonProperty("deviceId")
	private String deviceId;
	
//	@JsonProperty("userRole")
//	private String userRole;

//	public String getUserRole() {
//		return userRole;
//	}
//
//	public void setUserRole(String userRole) {
//		this.userRole = userRole;
//	}

	public String getId() {
		return id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserStatus getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(UserStatus accesskey) {
		this.accesskey = accesskey;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getScopeId() {
		return scopeId;
	}

	public void setScopeId(String scopeId) {
		this.scopeId = scopeId;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	
	
}
	
	
	
	
	
	
	

