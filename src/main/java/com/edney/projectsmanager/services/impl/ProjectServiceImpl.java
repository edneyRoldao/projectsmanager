package com.edney.projectsmanager.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

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
import com.edney.projectsmanager.services.ProjectService;

import jakarta.transaction.Transactional;

import static com.edney.projectsmanager.configs.AppMessage.*;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository repository;
	
	public ProjectServiceImpl(final ProjectRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Project> findAll() {
		return repository.findAllByDeletedFalse();
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		var project = getProjectById(id);
		checkDeletionIsValid(project);
		repository.logicalDeletion(project.getId());		
	}

	@Override
	public Project getProjectById(Long id) {
		var project = repository
				.findFirstByIdAndDeletedFalse(id)
				.orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND_ERROR_MSG.getDescription()));
		return project;
	}

	@Override
	public ProjectFormDTO getDataCreate() {		
		return new ProjectFormDTO(null, ProjectRisk.getList(), ProjectStatus.getList());
	}

	@Override
	public ProjectFormDTO getDataUpdate(Long projectId) {
		var project = getProjectById(projectId);
		return new ProjectFormDTO(project, ProjectRisk.getList(), ProjectStatus.getList());
	}

	@Override
	@Transactional
	public void createOrUpdate(ProjectRequest request) {
		var project = new Project(request);
		checkMemberAssignmentIsValid(project.getMember());		
		try {
			var projectSaved = repository.save(project);
			if (Objects.isNull(projectSaved)) 
				throw new RuntimeException(CREATE_PROJECT_ERROR_MSG.getDescription());			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateUpdateProjectException(e.getMessage());			
		}		
	}
	
	private void checkDeletionIsValid(Project project) {
		if (!project.getCanBeDeleted())
			throw new ProjectNotDeletedException(PROJECT_CANT_BE_DELETED_ERROR_MSG.getDescription());
	}
	
	private void checkMemberAssignmentIsValid(Member member) {
		try {
			if (member.getEmployee() == true) return;			
		} catch (Exception e) {
			throw new ProjectMemberAssignmentException(PROJECT_MEMBER_CANNOT_ASSIGN_ERROR_MSG.getDescription());
		}		
	}
	
}
