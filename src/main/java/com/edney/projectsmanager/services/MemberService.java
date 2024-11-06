package com.edney.projectsmanager.services;

import java.util.List;

import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.dtos.MemberRequest;
import com.edney.projectsmanager.dtos.MemberUpdateRequest;

public interface MemberService {

	List<Member> getAll();
	
	Member getById(Long id);
	
	void create(MemberRequest request);

	void update(MemberUpdateRequest request);
	
}
