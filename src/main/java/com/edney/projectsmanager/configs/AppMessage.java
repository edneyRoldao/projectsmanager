package com.edney.projectsmanager.configs;

public enum AppMessage {
	
	PROJECT_NOT_FOUND_ERROR_MSG("Project not found"),
	CREATE_PROJECT_ERROR_MSG("There was an unexpected error while trying to save the project");

	private final String description;
	
	AppMessage(String description) {
        this.description = description;
    }
	
    public String getDescription() {
        return description;
    }
	
}
