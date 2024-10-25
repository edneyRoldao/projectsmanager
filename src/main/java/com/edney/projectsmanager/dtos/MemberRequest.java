package com.edney.projectsmanager.dtos;


public class MemberRequest {

	private String name;
	private Boolean employee;
	private String assignment;
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
