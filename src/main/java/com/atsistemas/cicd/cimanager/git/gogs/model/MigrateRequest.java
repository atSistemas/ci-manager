package com.atsistemas.cicd.cimanager.git.gogs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class MigrateRequest {

	private String clone_addr;
	
	private int uid;
	
	private String repo_name;
}
