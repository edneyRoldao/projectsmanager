package com.edney.projectsmanager.services.impl;

import com.edney.projectsmanager.aspects.Log;
import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.domain.ProjectRisk;
import com.edney.projectsmanager.domain.ProjectStatus;
import com.edney.projectsmanager.dtos.ProjectFormDTO;
import com.edney.projectsmanager.dtos.ProjectRequest;
import com.edney.projectsmanager.exceptions.CreateUpdateProjectException;
import com.edney.projectsmanager.exceptions.ProjectMemberAssignmentException;
import com.edney.projectsmanager.exceptions.ProjectNotDeletedException;
import com.edney.projectsmanager.exceptions.ProjectNotFoundException;
import com.edney.projectsmanager.repositories.ProjectRepository;
import com.edney.projectsmanager.services.MemberService;
import com.edney.projectsmanager.services.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.edney.projectsmanager.configs.AppMessage.*;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

	private final MemberService memberService;
	private final ProjectRepository repository;

	@Log
	@Override
	public List<Project> findAll() {
		return repository.findAllByDeletedFalse();
	}

	@Log
	@Override
	@Transactional
	public void deleteById(Long id) {
		var project = getProjectById(id);
		checkDeletionIsValid(project);
		repository.logicalDeletion(project.getId());		
	}

	@Log
	@Override
	public Project getProjectById(Long id) {
        return repository
				.findFirstByIdAndDeletedFalse(id)
				.orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_ERROR_MSG.getDescription()));
	}

	@Log
	@Override
	public ProjectFormDTO getDataCreate() {
        var risks = ProjectRisk.getList();
		var statuses = ProjectStatus.getList();
		var members = memberService.getAll();
		return new ProjectFormDTO(null, risks, statuses, members);
	}

	@Log
	@Override
	public ProjectFormDTO getDataUpdate(Long projectId) {
		var project = getProjectById(projectId);
		var risks = ProjectRisk.getList();
		var statuses = ProjectStatus.getList();
		var members = memberService.getAll();
		return new ProjectFormDTO(project, risks, statuses, members);
	}

	@Log
	@Override
	@Transactional
	public void createOrUpdate(ProjectRequest request) {
		var project = new Project(request);
		project.setMember(memberService.getById(request.getMemberId()));
		checkMemberAssignmentIsValid(project.getMember());		

		try {
			repository.save(project);

        } catch (Exception e) {
			throw new CreateUpdateProjectException(e.getMessage());
		}		
	}
	
	private void checkDeletionIsValid(Project project) {
		if (!project.getCanBeDeleted())
			throw new ProjectNotDeletedException(PROJECT_CANT_BE_DELETED_ERROR_MSG.getDescription());
	}
	
	private void checkMemberAssignmentIsValid(Member member) {
		try {
			if (member.getEmployee())
				return;

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		throw new ProjectMemberAssignmentException(PROJECT_MEMBER_CANNOT_ASSIGN_ERROR_MSG.getDescription());
	}
	
}
