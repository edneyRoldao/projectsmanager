package com.edney.projectsmanager.services;

import java.util.List;

import com.edney.projectsmanager.domain.ProjectRisk;
import com.edney.projectsmanager.domain.ProjectStatus;

public interface ProjectService {

	default List<ProjectRisk> getProjectRisks() {
		return ProjectRisk.getList();
	}
	
	default List<ProjectStatus> getProjectStatuses() {
		return ProjectStatus.getList();
	}
	
}
