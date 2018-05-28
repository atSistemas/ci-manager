package com.atsistemas.cicd.cimanager.git.gogs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.atsistemas.cicd.cimanager.git.GitService;
import com.atsistemas.cicd.cimanager.git.GitServiceException;
import com.atsistemas.cicd.cimanager.git.gogs.model.MigrateRequest;
import com.atsistemas.cicd.cimanager.git.gogs.model.MigrateResponse;


@Service("gitService")
public class GogsAdapter implements GitService {
	
	private Logger logger = LoggerFactory.getLogger(GogsAdapter.class);

	@Value("${gogs.migrateUrl}")
	private String migrateUrl;
	
//	private String token = "763994e3b4e56b48fd8b787b9b43610bc4a60ab5";
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
			throw new GitServiceException("Error migrating repository");
		}
		
		if(createWebHook()) {
			logger.info("Webhook created");
		}
		else {
			logger.error("Error creating webhook");
			throw new GitServiceException("Error creating webhook");
		}
		
		
	}
	
	private boolean migrateRepo(String projectName, String repoUrl) {
		
		MigrateRequest migrateRequest = new MigrateRequest();
		migrateRequest.setClone_addr(repoUrl);
		migrateRequest.setRepo_name(projectName);
		migrateRequest.setUid(1);
		
		logger.debug("--> migrateUrl: {}",migrateUrl);
		
		HttpEntity<MigrateRequest> request = new HttpEntity<>(migrateRequest);
		MigrateResponse migrateResponse = restTemplate.postForObject(migrateUrl, request, MigrateResponse.class);
		
		logger.debug("MigrateResponse: " + migrateResponse.toString());
		
		//TODO check migrateResponse
		return true;
	}
	
	private boolean createWebHook() {
		
		//TODO
		return true;
	}

}
