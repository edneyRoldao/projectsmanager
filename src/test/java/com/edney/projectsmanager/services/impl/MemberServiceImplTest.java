package com.edney.projectsmanager.services.impl;

import com.edney.projectsmanager.NoContextBaseTest;
import com.edney.projectsmanager.repositories.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class MemberServiceImplTest extends NoContextBaseTest {

    @InjectMocks
    private MemberServiceImpl service;

    @Mock
    private MemberRepository repository;

    @Test
    void test() {
        Assertions.assertNotNull(repository);
    }

}
