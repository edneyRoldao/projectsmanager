package com.edney.projectsmanager.configs;

import lombok.Getter;

@Getter
public enum AppMessage {
	
	PROJECT_NOT_FOUND_ERROR_MSG("Project not found"),
	PROJECT_MEMBER_CANNOT_ASSIGN_ERROR_MSG("A member cannot be assigned to the project because he/she is not an employee."),
	PROJECT_CANT_BE_DELETED_ERROR_MSG("Project cannot be deleted due to forbidden status"),
	CREATE_PROJECT_ERROR_MSG("There was an unexpected error while trying to save the project");
	
	private final String description;
	
	AppMessage(String description) {
        this.description = description;
    }

}
