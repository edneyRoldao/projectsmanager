package com.edney.projectsmanager.exceptions;

public class ProjectNotDeletedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProjectNotDeletedException(String message) {
		super(message);
	}
	
}
