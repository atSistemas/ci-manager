package com.atsistemas.cicd.cimanager.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.atsistemas.cicd.ci_manager.generated.api.ProjectsApi;
import com.atsistemas.cicd.ci_manager.generated.model.CreateProjectRequest;
import com.atsistemas.cicd.ci_manager.generated.model.Project;
import com.atsistemas.cicd.cimanager.service.CiServiceException;
import com.atsistemas.cicd.cimanager.service.CiService;

import io.swagger.annotations.Api;

@Controller
@Api
public class ProjectsController implements ProjectsApi{
	
	private Logger logger = LoggerFactory.getLogger(ProjectsController.class);

	
	@Autowired
	private CiService ciService;

	@Override
	public ResponseEntity<Project> projectsPost(@Valid CreateProjectRequest createProjectRequest) {

		logger.debug("--> project name: {}",createProjectRequest.getProjectName());
		logger.debug("--> repo url: {}",createProjectRequest.getRepoUrl());
		
		try {
			ciService.createProject(createProjectRequest.getProjectName(), createProjectRequest.getRepoUrl());
		} catch (CiServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
}