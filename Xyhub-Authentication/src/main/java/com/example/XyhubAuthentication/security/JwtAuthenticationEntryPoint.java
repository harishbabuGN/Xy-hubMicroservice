package com.example.XyhubAuthentication.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		if(authException instanceof InsufficientAuthenticationException)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired or invalid");
		
		else if(authException instanceof BadCredentialsException)
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
		
		else
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unothorised");
	}
}
