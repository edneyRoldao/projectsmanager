package com.edney.projectsmanager.controller;

import java.util.List;

import com.edney.projectsmanager.aspects.Log;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.dtos.MemberRequest;
import com.edney.projectsmanager.services.MemberService;

@RestController
@RequestMapping("members/api")
@RequiredArgsConstructor
public class MemberAPIController {

	private final MemberService service;

	@Log
	@GetMapping("all")
	@ResponseStatus(HttpStatus.OK)
	public List<Member> getAll() {
		return service.getAll();
	}

	@Log
	@PostMapping("create")
	@ResponseStatus(HttpStatus.CREATED)
	public void createMember(@RequestBody @Valid MemberRequest request) {
		service.create(request);
	}
		
}
