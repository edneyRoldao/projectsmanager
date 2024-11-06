package com.edney.projectsmanager.repositories;

import com.edney.projectsmanager.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>{

    Optional<Member> findFirstById(Long id);

}
