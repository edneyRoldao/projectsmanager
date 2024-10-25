package com.edney.projectsmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edney.projectsmanager.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{

}
