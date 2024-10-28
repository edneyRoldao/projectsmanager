package com.edney.projectsmanager.exceptions;

import java.io.Serial;

public class MemberNotFoundException extends RuntimeException{

	@Serial
	private static final long serialVersionUID = 1L;

	public MemberNotFoundException(String message) {
		super(message);
	}
	
}
