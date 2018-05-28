package com.atsistemas.cicd.cimanager.platform.openshift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		} catch (ResourceFactoryException e) {
			logger.error("ResourceFactoryException",e);
			throw new PlatformServiceException("Caused by ResourceFactoryException",e);
		}
		IResource request = openshiftClient.getResourceFactory().stub(ResourceKind.TEMPLATE, resource);
		openshiftClient.create(request,"dev");
		
	}

}
