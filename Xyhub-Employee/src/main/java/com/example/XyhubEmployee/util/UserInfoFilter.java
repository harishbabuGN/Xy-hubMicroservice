package com.example.XyhubEmployee.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.XyhubCommon.Component.CurrentUser;


/**
 * This filter will extract the user information from the request which was set
 * in authentication-server before forwarding to the current service and update the
 * CurrentRequestUser bean. The CurrentRequestUser will be used later for
 * customizing API response based on userId and userRole
 * 
 * @see CurrentUser
 *
 */


@Component
public class UserInfoFilter extends OncePerRequestFilter{
	
	@Autowired
	CurrentUser currentUser;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		System.out.println("///////////////////"+request.getHeaders("name").hasMoreElements() != null
//				? request.getHeaders("name").nextElement()
//				: null);
		currentUser.setFirstName(request.getHeaders("name").hasMoreElements()
				? request.getHeaders("name").nextElement()
				: null);
		
		currentUser.setIpAddress(request.getRemoteAddr());
		logger.info(request.getRemoteAddr());
		request.getHeaderNames();
		System.out.println(request.getHeaderNames());
        System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]"+currentUser.getName());
		logger.info(currentUser.getIpAddress());
		filterChain.doFilter(request, response);
		
	}

}
