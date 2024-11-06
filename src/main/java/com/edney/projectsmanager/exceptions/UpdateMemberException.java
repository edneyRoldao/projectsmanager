package com.edney.projectsmanager.exceptions;

import java.io.Serial;

public class UpdateMemberException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public UpdateMemberException(String message) {
		super(message);
	}

}
