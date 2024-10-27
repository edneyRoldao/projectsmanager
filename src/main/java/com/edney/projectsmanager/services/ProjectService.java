package com.edney.projectsmanager.services;

import java.util.List;

import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.dtos.ProjectRequest;
import com.edney.projectsmanager.dtos.ProjectFormDTO;

public interface ProjectService {
	
	List<Project> findAll();
	
	void deleteById(Long id);
	
	Project getProjectById(Long id);
	
	void createOrUpdate(ProjectRequest project);

	ProjectFormDTO getDataCreate();

	ProjectFormDTO getDataUpdate(Long projectId);

}
