package com.edney.projectsmanager.services.impl;

import com.edney.projectsmanager.NoContextBaseTest;
import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.dtos.MemberRequest;
import com.edney.projectsmanager.exceptions.CreateMemberException;
import com.edney.projectsmanager.repositories.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberServiceImplTest extends NoContextBaseTest {

    @InjectMocks
    private MemberServiceImpl service;

    @Mock
    private MemberRepository repository;

    @Test
    void shouldReturnMembersList() {
        // mock
        List<Member> members = loadFile("domain/member-list");

        when(repository.findAll()).thenReturn(members);

        var result = service.getAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), members.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnOneMember() {
        var memberId = 2L;
        Member member = loadFile("domain/member", Member.class);

        when(repository.getReferenceById(memberId)).thenReturn(member);

        var result = service.getById(memberId);

        assertNotNull(result);
        assertEquals(member.getDocument(), result.getDocument());
        verify(repository, times(1)).getReferenceById(memberId);
    }

    @Test
    void shouldCreateMember() {
        MemberRequest request = loadFile("dtos/member-request", MemberRequest.class);

        service.create(request);

        verify(repository, times(1)).save(any(Member.class));
    }

    @Test
    void whenCreateMemberShouldThrowException() {
        MemberRequest request = loadFile("dtos/member-request", MemberRequest.class);

        when(repository.save(any(Member.class))).thenThrow(new RuntimeException());

        assertThrows(CreateMemberException.class, () -> service.create(request));
    }

}
