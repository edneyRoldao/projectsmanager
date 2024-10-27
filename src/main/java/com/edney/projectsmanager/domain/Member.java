package com.edney.projectsmanager.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "members")
public class Member implements Serializable {

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

	public Member() {
		
	}
	
	public Member(Long id) {
		this.id = id;
	}
	
	public Member(Long id, String name, Boolean employee, String assignment, String document) {
		super();
		this.id = id;
		this.name = name;
		this.employee = employee;
		this.assignment = assignment;
		this.document = document;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getEmployee() {
		return employee;
	}

	public void setEmployee(Boolean employee) {
		this.employee = employee;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(assignment, employee, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(assignment, other.assignment) && Objects.equals(employee, other.employee)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", employee=" + employee + ", assignment=" + assignment
				+ ", document=" + document + "]";
	}
	
}
