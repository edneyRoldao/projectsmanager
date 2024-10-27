package com.edney.projectsmanager.dtos;

import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.domain.ProjectRisk;
import com.edney.projectsmanager.domain.ProjectStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Setter
@Getter
@ToString
public class ProjectFormDTO  implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private ProjectRequest project;
	private List<DataSelectDTO<Member>> members;
	private List<DataSelectDTO<ProjectRisk>> risks;
	private List<DataSelectDTO<ProjectStatus>> statuses;

	public ProjectFormDTO (Project project, 
			               List<ProjectRisk> risks, 
			               List<ProjectStatus> statuses, 
			               List<Member> members) {
		if (Objects.isNull(project)) {
			buildOnCreate(risks, statuses, members);
		} else {
			buildOnUpdate(project, risks, statuses, members);
		}
	}
	
	private void buildOnCreate(List<ProjectRisk> risks, 
			                   List<ProjectStatus> statuses, 
			                   List<Member> members) {
		this.project = new ProjectRequest();
		this.risks = risks.stream().map(r -> new DataSelectDTO<>(r, false)).toList();
		this.statuses = statuses.stream().map(s -> new DataSelectDTO<>(s, false)).toList();
		this.members = members.stream().map(m -> new DataSelectDTO<>(m, false)).toList();
	}
		
	private void buildOnUpdate(Project project, 
			                   List<ProjectRisk> risks, 
			                   List<ProjectStatus> statuses, 
			                   List<Member> members) {
		this.project = new ProjectRequest(project);
		this.risks = risks
				.stream()
				.map(r -> new DataSelectDTO<>(r, project.getRisk().equals(r)))
				.toList();
		this.statuses = statuses
				.stream()
				.map(s -> new DataSelectDTO<>(s, project.getStatus().equals(s)))
				.toList();
		this.members = members
				.stream()
				.map(m -> new DataSelectDTO<>(m, project.getMember().getId().equals(m.getId())))
				.toList();		
	}

}
