package com.example.XyhubAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.annotation.RequestScope;

import com.example.XyhubCommon.Component.CurrentUser;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.XyhubAuthentication")
@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients(basePackages = "com.example.XyhubAuthentication.restclient")
public class XyhubAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyhubAuthenticationApplication.class, args);
	}

	@Bean
	@RequestScope
	public CurrentUser requestedUserInfo() {
		return new CurrentUser();
	}
}
