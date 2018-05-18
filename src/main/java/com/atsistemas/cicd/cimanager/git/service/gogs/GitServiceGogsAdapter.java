package com.atsistemas.cicd.cimanager.git.service.gogs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.atsistemas.cicd.cimanager.git.service.GitService;
import com.atsistemas.cicd.cimanager.git.service.GitServiceException;
import com.atsistemas.cicd.cimanager.git.service.gogs.model.MigrateRequest;
import com.atsistemas.cicd.cimanager.git.service.gogs.model.MigrateResponse;


@Service("gitService")
public class GitServiceGogsAdapter implements GitService {
	
	private Logger logger = LoggerFactory.getLogger(GitServiceGogsAdapter.class);

	private String gogsUrl = "http://localhost:10080";
	private String gogsUser = "dani";
	private String gogsPasswd = "1234";
	private String ciManagerToken = "763994e3b4e56b48fd8b787b9b43610bc4a60ab5";
	
//	private GogsClient client;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void createProjectFromRepo(String projectName, String repoUrl) throws GitServiceException {

		
		if(migrateRepo(projectName,repoUrl)) {
			logger.info("Repository {} migrated",repoUrl);
			createWebHook();
		}
		else {
			logger.error("Error migrating repository {}",repoUrl);
			throw new GitServiceException();
		}
		
		
		
	}
	
	private boolean migrateRepo(String projectName, String repoUrl) {
		
		String url = gogsUrl + "/api/v1/repos/migrate"; 
		MigrateRequest migrateRequest = new MigrateRequest();
		migrateRequest.setClone_addr(repoUrl);
		migrateRequest.setRepo_name(projectName);
		migrateRequest.setUid(1);
		
		HttpEntity<MigrateRequest> request = new HttpEntity<>(migrateRequest);
		MigrateResponse migrateResponse = restTemplate.postForObject(url, request, MigrateResponse.class);
		
		
		logger.debug("MigrateResponse: " + migrateResponse.toString());
		//TODO
		return true;
	}
	
	private boolean createWebHook() {
		
		//TODO
		return true;
	}

}
