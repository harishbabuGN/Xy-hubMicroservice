package com.example.XyhubAuthentication;


import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableEurekaClient
@EnableZuulProxy
//@EnableFeignClients
@EnableScheduling
@ComponentScan(basePackages = "com.example")
@EnableSwagger2
public class SwaggerConfig {
	
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	
//	@Bean
//	@RequestScope
//	public CurrentUser requestedUserInfo() {
//		return new CurrentUser();
//	}
	@Configuration
	@EnableSwagger2
	public class SpringFoxConfig {
		@Bean
		public Docket apiDocket() {
			return new Docket(DocumentationType.SWAGGER_2).securityContexts(Arrays.asList(securityContext()))
					.securitySchemes(Arrays.asList(apiKey())).select().apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any()).build();
		}
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}
