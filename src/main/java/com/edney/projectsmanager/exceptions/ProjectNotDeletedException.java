package com.edney.projectsmanager.exceptions;

import java.io.Serial;

public class ProjectNotDeletedException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = 1L;

	public ProjectNotDeletedException(String message) {
		super(message);
	}
	
}
