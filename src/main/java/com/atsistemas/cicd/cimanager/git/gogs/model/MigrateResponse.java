package com.atsistemas.cicd.cimanager.git.gogs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MigrateResponse {
	
	private String id;
	
	private Owner owner;
	
	private String full_name;
	
	//TODO
	
	
	private Permissions permissions; 
}
