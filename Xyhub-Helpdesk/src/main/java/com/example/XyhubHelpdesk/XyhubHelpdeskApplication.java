package com.example.XyhubHelpdesk;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EntityScan(basePackages = "com.example.XyhubHelpdesk.Entity")
public class XyhubHelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyhubHelpdeskApplication.class, args);
	}
	
//	
//	@Bean
//	public JpaMappingContext jpaMappingContext(EntityManagerFactory entityManagerFactory) {
//	    return new DefaultJpaMappingContext(entityManagerFactory);
//	}


}
