package com.edney.projectsmanager.services.impl;

import com.edney.projectsmanager.aspects.Log;
import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.dtos.MemberRequest;
import com.edney.projectsmanager.exceptions.CreateMemberException;
import com.edney.projectsmanager.exceptions.MemberNotFoundException;
import com.edney.projectsmanager.repositories.MemberRepository;
import com.edney.projectsmanager.services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.edney.projectsmanager.configs.AppMessage.MEMBER_NOT_FOUND_ERROR_MSG;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository repository;

	@Log
	@Override
	public List<Member> getAll() {
		return repository.findAll();
	}

	@Log
	@Override
	public Member getById(Long id) {
		var member = repository.getReferenceById(id);

		if (Objects.isNull(member) || Objects.isNull(member.getId()))
			throw new MemberNotFoundException(MEMBER_NOT_FOUND_ERROR_MSG.getDescription());

		return member;
	}

	@Log
	@Override
	public void create(MemberRequest request) {
		try {
			var model = convertMemberRequestToModel(request);
			repository.save(model);
			
		} catch (Exception e) {
			throw new CreateMemberException(e.getMessage());
		}
	}
	
	private Member convertMemberRequestToModel(MemberRequest request) {
		var model = new Member();
		BeanUtils.copyProperties(request, model);
		return model;
	}
	
}
