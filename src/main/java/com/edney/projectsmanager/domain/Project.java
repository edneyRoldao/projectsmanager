package com.edney.projectsmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import jakarta.persistence.*;

import com.edney.projectsmanager.dtos.ProjectRequest;


@Entity
@Table(name = "projects")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private String description;

    private LocalDate initDate;

    private LocalDate endDate;

    private LocalDate realEndDate;
    
    @JsonIgnore
    private Boolean deleted;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    private ProjectRisk risk;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
    
    @Transient
    private Boolean canBeDeleted;
    
	public Project(ProjectRequest request) {
		BeanUtils.copyProperties(request, this);
		this.risk = request.getRisk().getValue();
		this.status = request.getStatus().getValue();
		this.member = request.getMember().getValue();		
	}
    
	public Project() {
	}

	public Project(Long id, String name, String description, LocalDate initDate, LocalDate endDate,
			LocalDate realEndDate, Boolean deleted, BigDecimal budget, ProjectRisk risk, ProjectStatus status,
			Member member, Boolean canBeDeleted) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.initDate = initDate;
		this.endDate = endDate;
		this.realEndDate = realEndDate;
		this.deleted = deleted;
		this.budget = budget;
		this.risk = risk;
		this.status = status;
		this.member = member;
		this.canBeDeleted = canBeDeleted;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getInitDate() {
		return initDate;
	}

	public void setInitDate(LocalDate initDate) {
		this.initDate = initDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(LocalDate realEndDate) {
		this.realEndDate = realEndDate;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public ProjectRisk getRisk() {
		return risk;
	}

	public void setRisk(ProjectRisk risk) {
		this.risk = risk;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getCanBeDeleted() {
		switch (status) {
			case STARTED:
			case ONGOING:
			case FINISHED:
				return false;
			default:
				return true;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(id, other.id);
	}
        
}