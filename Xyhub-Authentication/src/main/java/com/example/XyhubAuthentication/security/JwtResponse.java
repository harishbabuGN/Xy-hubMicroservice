package com.example.XyhubAuthentication.security;

import java.io.Serializable;

import com.example.XyhubCommon.constants.XyhubUserDetails;

//import com.example.XyhubAuthentication.model.XyhubUserDetails;






public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String jwttoken;
	
	private final XyhubUserDetails user;

	public JwtResponse(String jwttoken, XyhubUserDetails appUser) {
		this.jwttoken = jwttoken;
		this.user = appUser;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public XyhubUserDetails getUser() {
		return this.user;
	}
	
}
