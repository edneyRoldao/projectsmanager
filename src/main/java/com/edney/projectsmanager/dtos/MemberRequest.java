package com.edney.projectsmanager.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class MemberRequest {

	@NotBlank(message = "member's name cannot be empty")
	private String name;
	
	@NotNull(message = "member's employee field should be provided with true or false")
	private Boolean employee;
	
	@NotBlank(message = "member's assignment cannot be empty") 
	private String assignment;

	@NotBlank(message = "member's document cannot be empty")
	private String document;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getEmployee() {
		return employee;
	}
	
	public void setEmployee(Boolean employee) {
		this.employee = employee;
	}
	
	public String getAssignment() {
		return assignment;
	}
	
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
	
}
