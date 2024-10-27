package com.edney.projectsmanager.exceptions;

import java.io.Serial;

public class CreateMemberException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = 1L;

	public CreateMemberException(String message) {
		super(message);
	}
	
}
