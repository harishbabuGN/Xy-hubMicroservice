package com.example.XyhubAuthentication.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//import com.example.XyhubAuthentication.Controller.UserController;
//import com.example.XyhubAuthentication.model.XyhubUserDetails;
import com.example.XyhubAuthentication.restclient.XyHubUserClient;
import com.example.XyhubCommon.constants.XyhubUserDetails;



@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private XyHubUserClient xyHubUserClient;
	
//	@Autowired
//	UserController userController;
	

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		XyhubUserDetails user =null;

		user = xyHubUserClient.loadUserByUsername(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));

		if (user.getStatus().equals("ACTIVE")) {

			return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}

		else {
			return new User(user.getUsername(), user.getPassword(), false, true, true, true, grantedAuthorities);
		}

	}

	public XyhubUserDetails getAppUser(String username) {

		XyhubUserDetails user = xyHubUserClient.loadUserByUsername(username);

		if (user != null) {

			if (user.getStatus()!=null && user.getStatus().equals("ACTIVE"))
			{
				return user;
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not active");
			}
			
			
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found");
		}

	}

}
