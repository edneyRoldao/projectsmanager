package com.edney.projectsmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

import com.edney.projectsmanager.domain.ProjectRisk;
import com.edney.projectsmanager.domain.ProjectStatus;
import com.edney.projectsmanager.services.ProjectService;

@RestController
@RequestMapping("projects/api")
public class ProjectAPIController {

	private final ProjectService service;
	
	public ProjectAPIController(ProjectService service) {
		this.service = service;
	}
	
	@GetMapping("risks")
	@ResponseStatus(HttpStatus.OK)
	public List<ProjectRisk> getProjectRisks() {
		return service.getProjectRisks();
	}

	@GetMapping("statuses")
	@ResponseStatus(HttpStatus.OK)
	public List<ProjectStatus> getProjectStatuses() {
		return service.getProjectStatuses();
	}
	
}
