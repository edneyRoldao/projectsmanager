package com.edney.projectsmanager.configs;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.edney.projectsmanager.exceptions.CreateMemberException;
import com.edney.projectsmanager.exceptions.ProjectNotDeletedException;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class ProjectManagerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CreateMemberException.class)
	public final String handler(CreateMemberException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ProjectNotDeletedException.class)
	public final String handler(ProjectNotDeletedException e) {
		return e.getMessage();
	}

}
