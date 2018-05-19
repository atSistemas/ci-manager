package com.atsistemas.cicd.cimanager.service;

public interface CiService {

	public void createProject(String projectName, String repoUrl) throws CiServiceException;
	
}
