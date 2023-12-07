package com.example.XyhubAuthentication;



import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Component
@Primary
@EnableAutoConfiguration
public class SwaggerResorce implements SwaggerResourcesProvider {

	

	@Override
	public List<SwaggerResource> get() {
		
		List<SwaggerResource> resources = new ArrayList<SwaggerResource>();

		resources.add(swaggerResource("XYHUB AUTHENTICATION", "/v2/api-docs", "2.0"));
//		resources.add(swaggerResource("XYHUB-EMPLOYEE", "/xyhub-Employee/v2/api-docs", "2.0"));
		resources.add(swaggerResource("XYHUB-EMPLOYEE", "/xyhub-Employee/v2/api-docs", "2.0"));
		resources.add(swaggerResource("XYHUB-HELPDESK", "/xyhub-Helpdesk/v2/api-docs", "2.0"));
		resources.add(swaggerResource("XYHUB-TRAINING", "/xyhub-Training/v2/api-docs", "2.0"));
		return resources;
	}

	/**
	 * Creates and returns the new Swagger resource to add into proxy Swagger
	 * 
	 * @param name: Name of the definition / microservice
	 * @param location: Path of the microservice
	 * @param version
	 * @return SwaggerResource
	 */
	
	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
}
