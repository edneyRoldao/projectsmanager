package com.edney.projectsmanager.exceptions;

import java.io.Serial;

public class CreateUpdateProjectException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = 1L;

	public CreateUpdateProjectException(String message) {
		super(message);
	}
	
}
