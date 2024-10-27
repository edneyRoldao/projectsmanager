package com.edney.projectsmanager.exceptions;

import java.io.Serial;

public class ProjectMemberAssignmentException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = 1L;

	public ProjectMemberAssignmentException(String message) {
		super(message);
	}
	
}
