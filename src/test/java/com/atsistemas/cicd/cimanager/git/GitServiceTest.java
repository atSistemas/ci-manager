package com.atsistemas.cicd.cimanager.git;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.atsistemas.cicd.cimanager.git.GitService;
import com.atsistemas.cicd.cimanager.git.GitServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.yml")
@ActiveProfiles("test")
public class GitServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(GitServiceTest.class);
	
	@Autowired
	GitService gitService;

	@Test
	public void testCreateProjectFromRepo() {
		
		
		String projectName = "polaris";
		String repoUrl = "https://github.com/atSistemas/polaris.git";
		try {
			gitService.createProjectFromRepo(projectName, repoUrl);
		} catch (GitServiceException e) {
			logger.error("GitServiceException",e);
			fail();
		}
		
	}

}
