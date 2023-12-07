package com.example.XyhubAuthentication.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.XyhubCommon.constants.XyhubUserDetails;

//import com.example.XyhubAuthentication.model.XyhubUserDetails;

import io.jsonwebtoken.Claims;

@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		XyhubUserDetails user = userDetailsService.getAppUser(authenticationRequest.getUsername().toLowerCase());

		authenticate(authenticationRequest.getUsername().toLowerCase(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername().toLowerCase());

		final String token = jwtTokenUtil.generateToken( user,userDetails);

		XyhubUserDetails appUser = userDetailsService.getAppUser(authenticationRequest.getUsername());

		return ResponseEntity.ok(new JwtResponse(token, appUser));
	
	}

	
	
	@RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
	public RefreshTokenResponse refreshtoken(HttpServletRequest request)
			throws Exception {
		// From the HttpRequest get the claims

		final String requestTokenHeader = request.getHeader("Authorization");

		String sessionId = null;
		String jwtToken = null;
		String username = null;

		XyhubUserDetails userDetails = null;

		boolean authorize = false;

		Map<String, Object> expectedMap = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			Claims claims = jwtTokenUtil.getAllClaimsFromTokenForRF(jwtToken);
			expectedMap = getMapFromIoJsonwebtokenClaims(claims);

			username = expectedMap.get("sub") != null ? expectedMap.get("sub").toString().toLowerCase() : null;
				String apiToken = jwtTokenUtil.generateConumerToken(userDetails);

				return new RefreshTokenResponse(apiToken, "");

			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " invalid token ");
			}

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			logger.info("login successful for the username: " + username);
		} catch (DisabledException e) {
			logger.error("DisabledException for login attempt for the username: " + username);
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			logger.error("BadCredentialsException for login attempt for the username: " + username);
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(Claims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}

}
