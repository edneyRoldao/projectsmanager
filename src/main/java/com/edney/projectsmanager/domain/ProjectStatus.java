package com.edney.projectsmanager.domain;

import java.util.List;
import java.util.stream.Stream;

public enum ProjectStatus {

	STARTED,
	PLANNED,
	ONGOING,
	FINISHED,
	CANCELLED,
	ANALYSIS_DONE,
	UNDER_ANALYSIS,
	ANALYSIS_APPROVED;
	
	public static List<ProjectStatus> getList() {
		return Stream.of(values()).toList();
	}
	
}
