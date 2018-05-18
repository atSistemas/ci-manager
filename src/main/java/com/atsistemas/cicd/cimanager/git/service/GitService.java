package com.atsistemas.cicd.cimanager.git.service;

public interface GitService {
	
	public void createProjectFromRepo(String projectName,String repoUrl) throws GitServiceException;

}
