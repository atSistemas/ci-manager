package com.atsistemas.cicd.cimanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import com.openshift.restclient.ClientBuilder;
import com.openshift.restclient.IClient;

@Configuration
public class ApplicationConfiguration {

	@Value("${gogs.user}")
	private String gogsUser;
	
	@Value("${gogs.passwd}")
	private String gogsPasswd;
	
	@Value("${openshift.url}")
	private String openshiftUrl;
	
	@Value("${openshift.user}")
	private String openshiftUser;
	
	@Value("${openshift.passwd}")
	private String openshiftPasswd;
	
	@Bean
	RestTemplate restTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(gogsUser, gogsPasswd));
		
		return restTemplate;
	}
	
	@Bean
	IClient openshiftClient() {
		
		return new ClientBuilder(openshiftUrl)
				.withUserName(openshiftUser)
				.withPassword(openshiftPasswd)
				.build();
	}
}
