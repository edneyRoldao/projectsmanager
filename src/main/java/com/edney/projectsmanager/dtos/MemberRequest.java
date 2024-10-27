package com.edney.projectsmanager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class MemberRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "member's name cannot be empty")
	private String name;
	
	@NotNull(message = "member's employee field should be provided with true or false")
	private Boolean employee;
	
	@NotBlank(message = "member's assignment cannot be empty") 
	private String assignment;

	@NotBlank(message = "member's document cannot be empty")
	private String document;

}
