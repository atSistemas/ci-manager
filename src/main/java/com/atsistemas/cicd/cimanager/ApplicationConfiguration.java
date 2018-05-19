package com.atsistemas.cicd.cimanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

	@Value("${gogs.user}")
	private String gogsUser;
	
	@Value("${gogs.passwd}")
	private String gogsPasswd;
	
	@Bean
	RestTemplate restTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(gogsUser, gogsPasswd));
		
		return restTemplate;
	}
}
