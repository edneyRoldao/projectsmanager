package com.edney.projectsmanager.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.domain.ProjectRisk;
import com.edney.projectsmanager.domain.ProjectStatus;


public class ProjectFormDTO  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ProjectRequest project;
	private List<DataSelectDTO<Member>> members;
	private List<DataSelectDTO<ProjectRisk>> risks;
	private List<DataSelectDTO<ProjectStatus>> statuses;

	public ProjectFormDTO (Project project, List<ProjectRisk> risks, List<ProjectStatus> statuses) {
		if (Objects.isNull(project)) {
			buildOnCreate(risks, statuses);
		} else {
			buildOnUpdate(project, risks, statuses);
		}
	}
	
	private void buildOnCreate(List<ProjectRisk> risks, List<ProjectStatus> statuses) {
		this.project = new ProjectRequest();
		this.risks = risks.stream().map(risk -> new DataSelectDTO<>(risk, false)).toList();
		this.statuses = statuses.stream().map(s -> new DataSelectDTO<>(s, false)).toList();
	}
		
	private void buildOnUpdate(Project project, List<ProjectRisk> risks, List<ProjectStatus> statuses) {
		this.project = new ProjectRequest(project);
		this.risks = risks
				.stream()
				.map(r -> new DataSelectDTO<>(r, project.getRisk().equals(r)))
				.toList();
		this.statuses = statuses
				.stream()
				.map(s -> new DataSelectDTO<>(s, project.getStatus().equals(s)))
				.toList();
	}

	
	public ProjectRequest getProject() {
		return project;
	}

	public void setProject(ProjectRequest project) {
		this.project = project;
	}

	public List<DataSelectDTO<Member>> getMembers() {
		return members;
	}

	public void setMembers(List<DataSelectDTO<Member>> members) {
		this.members = members;
	}

	public List<DataSelectDTO<ProjectRisk>> getRisks() {
		return risks;
	}

	public void setRisks(List<DataSelectDTO<ProjectRisk>> risks) {
		this.risks = risks;
	}

	public List<DataSelectDTO<ProjectStatus>> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<DataSelectDTO<ProjectStatus>> statuses) {
		this.statuses = statuses;
	}
		
}
