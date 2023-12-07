package com.example.XyhubHelpdesk.constants;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.stereotype.Component;

import com.example.XyhubCommon.enumType.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserClient{

	@JsonProperty("id")
	private String id;

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	@JsonProperty("createdAt")
	private Date createdAt;

	@JsonProperty("uid")
	private String uid;
	
	
	@JsonProperty("name")
	private String name;

	@JsonProperty("status")
	private UserStatus status;

	@JsonProperty("accesskey")
	private String accesskey;

	@JsonProperty("permission")
	private Integer permission;

	@JsonProperty("resetPasswordToken")
	private String resetPasswordToken;
	
	@JsonProperty("scopeId")
	private String scopeId;
	
	@JsonProperty("isSuperAdmin")
	private boolean isSuperAdmin;

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

	@Column(unique = true, nullable = true)
	private String email;
	
	@Column(name="os_type")
	private String osType;
	
	@Column(name="device_id")
	private String deviceId;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

//	@Lob
//	@Type(type = "org.hibernate.type.ImageType")
//	@JsonIgnore
//	private byte[] image;
//
//	@JsonIgnore
//	@Column(name = "mime_type")
//	private String mimeType;
//
//	@Column(name = "file_name")
//	@JsonIgnore
//	private String fileName;

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

//	@Column(name = "user_role")
//	private String userRole;
//
////	public User() {
////
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	public String getUserRole() {
//		return userRole;
//	}
//
//	public void setUserRole(String userRole) {
//		this.userRole = userRole;
//	}

	public Object map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getScopeId() {
		return scopeId;
	}

	public void setScopeId(String scopeId) {
		this.scopeId = scopeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	
	
	
	
	
	

}