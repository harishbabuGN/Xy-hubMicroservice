package com.example.XyhubAuthentication.security;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;


import com.example.XyhubCommon.Component.CurrentUser;
import com.example.XyhubCommon.constants.AuthConstants;
import com.netflix.zuul.context.RequestContext;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
//    @Autowired
//    CurrentUser currentUser;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		

		String username = null;
		String jwtToken = null;
		
	String isRefreshToken=null;
		
	
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			isRefreshToken = request.getHeader("isRefreshToken");
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				logger.warn("JWT Token: " + jwtToken + " has expired");
				isRefreshToken = request.getHeader("isRefreshToken");
				String requestURL = request.getRequestURL().toString();
				// allow for Refresh Token creation if following conditions are true.
				if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshToken")) {
					allowForRefreshToken(e, request);
				} else
					request.setAttribute("exception", e);	
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username.toLowerCase());
//			int status = jwtUserDetailsService.getExistingLoggerUser(username.toLowerCase(), sessionId);

			if (isRefreshToken != null && isRefreshToken.equals("true")) {

				logger.info("refresh token");
			}
//			} else {
//
//				if (status == 1) {
//					 response.sendError(HttpServletResponse.SC_FORBIDDEN, " session expired please login again");
//				} else if (status == 2) {
//					response.sendError(HttpServletResponse.SC_FORBIDDEN,
//							"your account has been disabled. please contact administrator");
//				}
//			}
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

				// Adding user attributes for farwording services
				RequestContext ctx = RequestContext.getCurrentContext();
				ctx.addZuulRequestHeader(AuthConstants.USERNAME, (String) jwtTokenUtil.getNamedClaimFromToken(jwtToken, AuthConstants.USERNAME));
				ctx.addZuulRequestHeader(AuthConstants.NAME,
						(String) jwtTokenUtil.getNamedClaimFromToken(jwtToken, AuthConstants.NAME));
				System.out.println("users"+(String) jwtTokenUtil.getNamedClaimFromToken(jwtToken, AuthConstants.NAME));
				ctx.addZuulRequestHeader(AuthConstants.USER_ID,
						(String) jwtTokenUtil.getNamedClaimFromToken(jwtToken, AuthConstants.USER_ID));
				ctx.addZuulRequestHeader(AuthConstants.USER_ROLE,
						(String) jwtTokenUtil.getNamedClaimFromToken(jwtToken, AuthConstants.USER_ROLE));
				ctx.addZuulRequestHeader(AuthConstants.SCOPE_ID,
						(String) jwtTokenUtil.getNamedClaimFromToken(jwtToken, AuthConstants.SCOPE_ID));
				ctx.addZuulRequestHeader(AuthConstants.PERMISSIONS,
						(String) jwtTokenUtil.getNamedClaimFromToken(jwtToken, AuthConstants.PERMISSIONS));
			}
		}
		chain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	    AntPathMatcher pathMatcher = new AntPathMatcher();
	    return Arrays.stream(WebSecurityConfig.NON_SECURE_PATHS)
	            .anyMatch(url -> pathMatcher.match(url, request.getServletPath()));
	}
	
	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		// Set the claims so that in controller we will be using it to create
		// new JWT
		request.setAttribute("claims", ex.getClaims());

	}
	

}
