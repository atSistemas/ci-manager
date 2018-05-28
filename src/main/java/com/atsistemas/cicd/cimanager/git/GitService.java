package com.atsistemas.cicd.cimanager.git;

public interface GitService {
	
	public void createProjectFromRepo(String projectName,String repoUrl) throws GitServiceException;

}
