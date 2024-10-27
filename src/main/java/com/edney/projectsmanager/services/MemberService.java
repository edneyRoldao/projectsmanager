package com.edney.projectsmanager.services;

import java.util.List;

import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.dtos.MemberRequest;

public interface MemberService {

	List<Member> getAll();
	
	Member getById(Long id);
	
	void create(MemberRequest request);
	
}
