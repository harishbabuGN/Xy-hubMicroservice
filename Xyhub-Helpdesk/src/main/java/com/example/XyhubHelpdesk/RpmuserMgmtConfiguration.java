package com.example.XyhubHelpdesk;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableEurekaClient
@EnableScheduling
@EnableFeignClients
public class RpmuserMgmtConfiguration {

//	@Bean
//	public <T> RpmCache<T> getCacheConnection() {
//		return new RpmCache<>();
//	}
//
//	@Bean
//	public <T> RpmPublisher<T> getPublisher() {
//		return new RpmPublisher<>();
//	}

	/**
	 * Swagger Configuration
	 * 
	 * @return Docket bean
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey())).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfo("Profile Xyhub-Helpdesk API Documentation", "", "1.0", null,
						new Contact("Xyram Software Solutions", null, null), null, null, Collections.emptyList()));
	}

	/**
	 * Providing this module helps in avoid serialization of lazy loaded objects in
	 * API response
	 * 
	 * @return Hibernate5Module bean
	 */
//	@Bean
//	public Module datatypeHibernateModule() {
//		return new Hibernate5Module();
//	}

//	@Bean
//	@RequestScope()
//	public CurrentUser requestedUserInfo() {
//		return new CurrentUser();
//	}
//
//	@Bean
//	AuditorAware<String> auditorProvider() {
//		return new AuditorAwareImpl();
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

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
