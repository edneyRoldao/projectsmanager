package com.edney.projectsmanager.domain;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "members")
public class Member implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Boolean employee;
	
	@Column(nullable = false, length = 100)
	private String assignment;
	
	@Column(nullable = false, length = 30, unique = true)	
	private String document;

	public Member(Long id) {
		this.id = id;
	}

}
