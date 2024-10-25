package com.edney.projectsmanager.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.dtos.MemberRequest;
import com.edney.projectsmanager.exceptions.CreateMemberException;
import com.edney.projectsmanager.repositories.MemberRepository;
import com.edney.projectsmanager.services.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository repository;
	
	public MemberServiceImpl(final MemberRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Member> getAll() {
		return repository.findAll();
	}

	@Override
	public void create(MemberRequest request) {
		try {
			var model = convertMemberRequestToModel(request);
			repository.save(model);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateMemberException(e.getMessage());
		}
	}
	
	private Member convertMemberRequestToModel(MemberRequest request) {
		var model = new Member();
		BeanUtils.copyProperties(request, model);
		return model;
	}
	
}
