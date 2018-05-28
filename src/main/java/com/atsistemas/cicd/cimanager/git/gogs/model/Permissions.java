package com.atsistemas.cicd.cimanager.git.gogs.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

@Data
@JsonDeserialize
public class Permissions {
	
	private boolean admin;
	
	private boolean push;
	
	private boolean pull;

}
