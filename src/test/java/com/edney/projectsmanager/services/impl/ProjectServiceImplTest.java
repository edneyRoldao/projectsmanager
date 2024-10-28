package com.edney.projectsmanager.services.impl;

import com.edney.projectsmanager.NoContextBaseTest;
import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.dtos.ProjectRequest;
import com.edney.projectsmanager.exceptions.*;
import com.edney.projectsmanager.repositories.ProjectRepository;
import com.edney.projectsmanager.services.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest extends NoContextBaseTest {

    @InjectMocks
    private ProjectServiceImpl service;

    @Mock
    private MemberService memberService;

    @Mock
    private ProjectRepository repository;

    @Test
    void shouldLogicalDeleteTheProject() {
        Long projectId = 10L;
        Project project = loadFile("domain/project", Project.class);
        Optional<Project> projectOpt = Optional.of(project);

        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenReturn(projectOpt);

        service.deleteById(projectId);

        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
        verify(repository, times(1)).logicalDeletion(project.getId());
    }

    @Test
    void whenLogicalDeleteAndProjectNotFoundShouldThrowProjectNotFoundException() {
        Long projectId = 10L;

        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenThrow(new ProjectNotFoundException(""));

        assertThrows(ProjectNotFoundException.class, () -> service.deleteById(projectId));
        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
        verify(repository, times(0)).logicalDeletion(anyLong());
    }

    @Test
    void whenLocalDeleteAndProjectStatusIsStartedShouldThrowProjectNotDeletedException() {
        Long projectId = 10L;
        Project project = loadFile("domain/project-status-started", Project.class);
        Optional<Project> projectOpt = Optional.of(project);

        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenReturn(projectOpt);

        assertThrows(ProjectNotDeletedException.class, () -> service.deleteById(projectId));
        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
        verify(repository, times(0)).logicalDeletion(anyLong());
    }


    @Test
    void whenLocalDeleteAndProjectStatusIsOngoingShouldThrowProjectNotDeletedException() {
        Long projectId = 10L;
        Project project = loadFile("domain/project-status-ongoing", Project.class);
        Optional<Project> projectOpt = Optional.of(project);

        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenReturn(projectOpt);

        assertThrows(ProjectNotDeletedException.class, () -> service.deleteById(projectId));
        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
        verify(repository, times(0)).logicalDeletion(anyLong());
    }


    @Test
    void whenLocalDeleteAndProjectStatusIsFinishedShouldThrowProjectNotDeletedException() {
        Long projectId = 10L;
        Project project = loadFile("domain/project-status-finished", Project.class);
        Optional<Project> projectOpt = Optional.of(project);

        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenReturn(projectOpt);

        assertThrows(ProjectNotDeletedException.class, () -> service.deleteById(projectId));
        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
        verify(repository, times(0)).logicalDeletion(anyLong());
    }

    @Test
    void shouldGetProjectById() {
        Long projectId = 10L;
        Project project = loadFile("domain/project", Project.class);
        Optional<Project> projectOpt = Optional.of(project);

        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenReturn(projectOpt);

        var result = service.getProjectById(projectId);

        assertEquals(project.getName(), result.getName());
        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
    }

    @Test
    void whenGetProjectByIdShouldThrowProjectNotFoundException() {
        Long projectId = 10L;

        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenThrow(new ProjectNotFoundException(""));

        assertThrows(ProjectNotFoundException.class, () -> service.getProjectById(projectId));
        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
    }

    @Test
    void whenGetDataCreateShouldReturnProjectFormDTOWithProjectIdNull() {
        Member member = loadFile("domain/member", Member.class);
        List<Member> members = List.of(member);

        when(memberService.getAll()).thenReturn(members);

        var result = service.getDataCreate();

        assertNull(result.getProject().getId());
        verify(memberService, times(1)).getAll();
    }

    @Test
    void whenGetDataUpdateShouldReturnProjectFormDTOWithProjectId() {
        Long projectId = 10L;

        Project project = loadFile("domain/project-member", Project.class);
        Optional<Project> projectOpt = Optional.of(project);
        Member member = loadFile("domain/member", Member.class);
        List<Member> members = List.of(member);

        when(memberService.getAll()).thenReturn(members);
        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenReturn(projectOpt);

        var result = service.getDataUpdate(projectId);

        assertNotNull(result.getProject().getId());
        verify(memberService, times(1)).getAll();
        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
    }

    @Test
    void whenGetDataUpdateAndProjectDoesNotExistShouldThrowProjectNotFoundException() {
        Long projectId = 10L;

        when(repository.findFirstByIdAndDeletedFalse(projectId)).thenThrow(new ProjectNotFoundException(""));

        assertThrows(ProjectNotFoundException.class, () -> service.getDataUpdate(projectId));
        verify(memberService, times(0)).getAll();
        verify(repository, times(1)).findFirstByIdAndDeletedFalse(projectId);
    }

    @Test
    void whenCreateOrUpdateAndMemberDoesNotExistShouldThrowMemberNotFoundException() {
        ProjectRequest request = loadFile("dtos/project-request", ProjectRequest.class);

        when(memberService.getById(request.getMemberId())).thenThrow(new MemberNotFoundException(""));

        assertThrows(MemberNotFoundException.class, () -> service.createOrUpdate(request));
        verify(memberService, times(1)).getById(request.getMemberId());
        verify(repository, times(0)).save(any(Project.class));
    }

    @Test
    void whenCreateOrUpdateAndMemberIsNotAnEmployeeShouldThrowProjectMemberAssignmentException() {
        ProjectRequest request = loadFile("dtos/project-request", ProjectRequest.class);
        Member member = loadFile("domain/member-not-employee", Member.class);

        when(memberService.getById(request.getMemberId())).thenReturn(member);

        assertThrows(ProjectMemberAssignmentException.class, () -> service.createOrUpdate(request));
        verify(memberService, times(1)).getById(request.getMemberId());
        verify(repository, times(0)).save(any(Project.class));
    }

    @Test
    void whenCreateOrUpdateAndSaveErrorShouldThrowCreateUpdateProjectException() {
        ProjectRequest request = loadFile("dtos/project-request", ProjectRequest.class);
        Member member = loadFile("domain/member", Member.class);

        when(memberService.getById(request.getMemberId())).thenReturn(member);
        when(repository.save(any(Project.class))).thenThrow(new RuntimeException());

        assertThrows(CreateUpdateProjectException.class, () -> service.createOrUpdate(request));
        verify(memberService, times(1)).getById(request.getMemberId());
        verify(repository, times(1)).save(any(Project.class));
    }

    @Test
    void whenCreateOrUpdateShouldSaveProject() {
        ProjectRequest request = loadFile("dtos/project-request", ProjectRequest.class);
        Member member = loadFile("domain/member", Member.class);

        when(memberService.getById(request.getMemberId())).thenReturn(member);

        service.createOrUpdate(request);

        verify(memberService, times(1)).getById(request.getMemberId());
        verify(repository, times(1)).save(any(Project.class));
    }

}
