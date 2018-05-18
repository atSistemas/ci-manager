package com.atsistemas.cicd.cimanager.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.atsistemas.cicd.cimanager.git.service.GitService;

public class CiManagerFacadeImpl implements CiManagerFacade {
	
	@Autowired
	GitService gitService;

	@Override
	public void createProject(String projectName, String repoUrl) throws CiManagerException {
		// TODO Auto-generated method stub
		

	}

}
