package com.atsistemas.cicd.cimanager.platform.openshift;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.atsistemas.cicd.cimanager.platform.PlatformServiceException;
import com.atsistemas.cicd.cimanager.platform.PlatformService;
import com.openshift.restclient.IClient;
import com.openshift.restclient.ResourceKind;
import com.openshift.restclient.model.IResource;

@Service("platformService")
public class OpenShiftAdapter implements PlatformService {
	
	private Logger logger = LoggerFactory.getLogger(OpenShiftAdapter.class);
	
	@Autowired
	IClient openshiftClient;
	
	@Autowired
	ResourceFactory resourceFactory;
	
	@Override
	public void createApplication(String applicationName) throws PlatformServiceException {
		
		String resource = null;
		try {
			resource = resourceFactory.getTemplateAsString(applicationName);
			logger.debug("--> resource: {}",resource);
		} catch (ResourceFactoryException e) {
			logger.error("ResourceFactoryException",e);
			throw new PlatformServiceException("Caused by ResourceFactoryException",e);
		}
		
//		IResource request = openshiftClient.getResourceFactory().stub(ResourceKind.TEMPLATE, resource);
//		openshiftClient.create(request,"dev");
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		URI openShiftUri = null;
		try {
			openShiftUri = new URI("https://localhost:8443/oapi/v1/namespaces/dev/templates");
		} catch (URISyntaxException e) {
			logger.error("URISyntaxException",e);
			throw new PlatformServiceException("Caused by URISyntaxException",e);
		}
		
		String accessToken = "695dAGQfTkmGx2waJhxWYYF9yT2sGGuMcoGrj3_qpA4";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept", "application/json");
		headers.set("Authorization", "Bearer " + accessToken);
		
		RequestEntity<String> request = new RequestEntity<String>(resource, headers, HttpMethod.POST, openShiftUri);
		
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		
		logger.debug("--> response: {}",response.getBody());
		
	}

}
