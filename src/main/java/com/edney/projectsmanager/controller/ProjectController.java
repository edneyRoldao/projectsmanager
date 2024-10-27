package com.edney.projectsmanager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.dtos.ProjectFormDTO;
import com.edney.projectsmanager.dtos.ProjectRequest;
import com.edney.projectsmanager.services.ProjectService;

@Controller
@RequestMapping("projects")
public class ProjectController {

	private final ProjectService service;
	
	public ProjectController (final ProjectService service) {
		this.service = service;
	}
	
	@GetMapping("/create")
	public String renderCreatePage(Map<String, ProjectFormDTO> model) {
		model.put("data", service.getDataCreate());
		return "project-form";
	}
	
	@PostMapping("/save")
	public String create(@ModelAttribute ProjectRequest request) {
		System.out.println(request);
		service.createOrUpdate(request);
		return "redirect:/projects/all";
	}

	@GetMapping("/{projectId}/update")
	public String renderUpdatePage(Map<String, ProjectFormDTO> model, @PathVariable Long projectId) {
		model.put("data", service.getDataUpdate(projectId));
		return "project-form";
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
