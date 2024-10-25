package com.edney.projectsmanager.domain;

import java.util.List;
import java.util.stream.Stream;

public enum ProjectRisk {

	LOW, MEDIUM, HIGH;
	
	public static List<ProjectRisk> getList() {
		return Stream.of(values()).toList();
	}
	
}
