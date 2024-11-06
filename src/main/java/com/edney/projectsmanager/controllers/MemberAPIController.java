package com.edney.projectsmanager.controllers;

import com.edney.projectsmanager.aspects.Log;
import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.dtos.MemberRequest;
import com.edney.projectsmanager.dtos.MemberUpdateRequest;
import com.edney.projectsmanager.services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@Log
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Member getMemberById(@PathVariable Long id) {
		return service.getById(id);
	}

	@Log
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void updateMember(@RequestBody MemberUpdateRequest request) {
		service.update(request);
	}

}
