package com.edney.projectsmanager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.services.ProjectService;

@Controller
@RequestMapping("projects")
public class ProjectController {

	private final ProjectService service;
	
	public ProjectController (final ProjectService service) {
		this.service = service;
	}

	@GetMapping("/all")
	public String all(Map<String, List<Project>> model) {		
		var projects = service.findAll();
		model.put("projects", projects);
		return "project-list";
	}

	@GetMapping("/{id}")
	public String detail(Map<String, Project> model, @PathVariable Long id) {		
		var project = service.getProjectById(id);
		model.put("project", project);
		return "project-detail";
	}

	@PostMapping("/{id}/delete")
	public String delete(Map<String, Project> model, @PathVariable Long id) {		
		service.deleteById(id);
		return "redirect:/projects/all";
	}
	
}
