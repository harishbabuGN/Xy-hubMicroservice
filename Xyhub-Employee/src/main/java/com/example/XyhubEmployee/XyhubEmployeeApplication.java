package com.example.XyhubEmployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.XyhubCommon.Communication.PushNotificationCall;
import com.example.XyhubCommon.Communication.PushNotificationRequest;
import com.example.XyhubCommon.Component.CurrentUser;
import com.example.XyhubCommon.Config.PermissionConfig;



@EnableEurekaClient
@RefreshScope
@EnableAutoConfiguration
@EnableJpaRepositories
//@ComponentScan("com.example.XyhubEmployee.admin.model")
@Configuration
@SpringBootApplication
public class XyhubEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyhubEmployeeApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PermissionConfig permissionConfig() {
		return new PermissionConfig();
	}

    @Bean
    public PushNotificationCall pushNotificationCall() {
        return new PushNotificationCall();
    }
    @Bean
    public PushNotificationRequest pushNotificationRequest() {
        return new PushNotificationRequest();
    }
    
    
	@Bean
    public CurrentUser currentUser() {
        // Create and return an instance of CurrentUser
        return new CurrentUser();
    }

}
