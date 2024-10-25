package com.edney.projectsmanager.services;

import java.util.List;

import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.domain.ProjectRisk;
import com.edney.projectsmanager.domain.ProjectStatus;

public interface ProjectService {
	
	List<Project> findAll();
	
	void deleteById(Long id);
	
	Project getProjectById(Long id);
	
	void createOrUpdate(Project project);
	
	default List<ProjectRisk> getProjectRisks() {
		return ProjectRisk.getList();
	}
	
	default List<ProjectStatus> getProjectStatuses() {
		return ProjectStatus.getList();
	}

}
