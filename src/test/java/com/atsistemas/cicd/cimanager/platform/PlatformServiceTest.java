package com.atsistemas.cicd.cimanager.platform;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.yml")
@ActiveProfiles("test")
public class PlatformServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(PlatformServiceTest.class);
	

	@Autowired
	PlatformService platformService;
	
	@Test
	public void testCreateApplication() {
		
		String applicationName = "ci-manager";
		
		try {
			platformService.createApplication(applicationName);
		} catch (PlatformServiceException e) {
			logger.error("PlatformServiceException",e);
			fail();
		}
	}
	

}
