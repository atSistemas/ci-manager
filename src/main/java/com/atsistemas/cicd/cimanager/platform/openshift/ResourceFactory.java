package com.atsistemas.cicd.cimanager.platform.openshift;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@Service("resourceFactory")
public class ResourceFactory {
	
	private Logger logger = LoggerFactory.getLogger(ResourceFactory.class);

	public String getTemplateAsString(String applicationName) throws ResourceFactoryException {
		
		String templateFile = TemplateResolver.getTemplate(applicationName);
		String template = null;
		try {
			template = convertYamlToJson(ClassLoader.getSystemResourceAsStream(templateFile));
		} catch (JsonParseException e) {
			logger.error("JsonParseException",e);
			throw new ResourceFactoryException("Caused by JsonParseException",e);
		} catch (JsonMappingException e) {
			logger.error("JsonMappingException",e);
			throw new ResourceFactoryException("Caused by JsonMappingException",e);
		} catch (IOException e) {
			logger.error("IOException",e);
			throw new ResourceFactoryException("Caused by IOException",e);
		}
		return template;
		
	}
	
	private String convertYamlToJson(InputStream yaml) throws JsonParseException, JsonMappingException, IOException  {
	    ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
	    Object obj = yamlReader.readValue(yaml, Object.class);
	    ObjectMapper jsonWriter = new ObjectMapper();
	    return jsonWriter.writeValueAsString(obj);
	}
	
}
