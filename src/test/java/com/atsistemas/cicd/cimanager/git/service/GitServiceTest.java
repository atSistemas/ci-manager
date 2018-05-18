package com.atsistemas.cicd.cimanager.git.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GitServiceTest {
	
	@Autowired
	GitService gitService;

	@Test
	public void testCreateProjectFromRepo() {
		
		
		String projectName = "polaris";
		String repoUrl = "https://github.com/atSistemas/polaris.git";
		try {
			gitService.createProjectFromRepo(projectName, repoUrl);
		} catch (GitServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			fail();
		}
		
		
		
	}

}
