package com.example.XyhubAuthentication;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.XyhubAuthentication.security.NoPasswordEncoder;

import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;

public class XyhubAuthenticationConfiguration {
	
//	@Bean
//	public <T> ClockHealthCache<T> getCacheConnection() {
//		return new ClockHealthCache<T>();
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new NoPasswordEncoder();
	}

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).securityContexts(Arrays.asList(securityContext()))
//				.securitySchemes(Arrays.asList(apiKey())).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build()
//				.apiInfo(new ApiInfo("Authentication Server API Documentation",
//						"Handles Authentication and Authorization before forwarding request to respective services.<br/>Switch between <b>definitions</b> for other resources APIs.<br/><br/><b>Steps to Authorize</b><br/><br/>1. Use the login API to receicve JWT token.<br/>2. After receiving the token append \"Bearer \" and enter it in the Authorize section<br/>3. Click on \"Authorize\" button<br/>",
//						"1.0", null, new Contact("Xyram Software Solutions", "https://www.xyramsoft.com/", null), null,
//						null, Collections.EMPTY_LIST));
//
//	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}