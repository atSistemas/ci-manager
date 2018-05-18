package com.atsistemas.cicd.cimanager.service;

public interface CiManagerFacade {

	public void createProject(String projectName, String repoUrl) throws CiManagerException;
	
}
