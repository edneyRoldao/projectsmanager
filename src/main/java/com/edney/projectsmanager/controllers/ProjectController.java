package com.edney.projectsmanager.controllers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.edney.projectsmanager.aspects.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.dtos.ProjectFormDTO;
import com.edney.projectsmanager.dtos.ProjectRequest;
import com.edney.projectsmanager.services.ProjectService;
import utils.Tuple;

@Controller
@RequestMapping("projects")
@RequiredArgsConstructor

public class ProjectController {

	private final ProjectService service;

	@Log
	@GetMapping("/create")
	public String renderCreatePage(Map<String, ProjectFormDTO> model) {
		model.put("data", service.getDataCreate());
		return "project-form";
	}

	@Log
	@PostMapping("/save")
	public String create(@ModelAttribute ProjectRequest request) {
		service.createOrUpdate(request);
		return "redirect:/projects/all?message=Project SAVED with success";
	}

	@Log
	@GetMapping("/{projectId}/update")
	public String renderUpdatePage(Map<String, ProjectFormDTO> model, @PathVariable Long projectId) {
		model.put("data", service.getDataUpdate(projectId));
		return "project-form";
	}

	@Log
	@GetMapping("/all")
	public String all(@RequestParam(value = "message", required = false) String message,
					  Map<String, Tuple<String, List<Project>>> model) {
		var projects = service.findAll();
		Tuple<String, List<Project>> tuple = new Tuple<>(message, projects);
		model.put("tuple", tuple);

		return "project-list";
	}

	@Log
	@GetMapping("/{id}")
	public String detail(Map<String, Project> model, @PathVariable Long id) {		
		var project = service.getProjectById(id);
		model.put("project", project);
		return "project-detail";
	}

	@Log
	@PostMapping("/{id}/delete")
	public String delete(Map<String, Project> model, @PathVariable Long id) {		
		service.deleteById(id);
		return "redirect:/projects/all?message=Project DELETED with success";
	}

}
