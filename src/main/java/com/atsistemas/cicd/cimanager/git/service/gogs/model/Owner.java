package com.atsistemas.cicd.cimanager.git.service.gogs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {
	
	private String id;
	
	private String username;
	
	//TODO

}
