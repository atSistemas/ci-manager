package com.atsistemas.cicd.cimanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.cicd.cimanager.git.service.GitService;
import com.atsistemas.cicd.cimanager.git.service.GitServiceException;


@Service("ciService")
public class CiServiceFacadeImpl implements CiService {
	
	private Logger logger = LoggerFactory.getLogger(CiServiceFacadeImpl.class);

	
	@Autowired
	private GitService gitService;

	@Override
	public void createProject(String projectName, String repoUrl) throws CiServiceException {
		
		try {
			gitService.createProjectFromRepo(projectName, repoUrl);
		} catch (GitServiceException e) {
			logger.error("GitServiceException",e);
			throw new CiServiceException("Caused by GitServiceException",e);
		}
	}

}
