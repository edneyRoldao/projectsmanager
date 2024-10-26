package com.edney.projectsmanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project's name cannot be empty")
    @Column(nullable = false, length = 255)
    private String name;

    @NotBlank(message = "Project's description cannot be empty")
    @Column(nullable = false)
    private String description;

    private LocalDateTime initDate;

    private LocalDateTime endDate;

    private LocalDateTime realEndDate;
    
    @JsonIgnore
    private Boolean deleted;

    @DecimalMin(value = "1.00", message = "Project's budget should at least 1.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal budget;

    @NotNull(message = "Project's risk cannot be null")
    @Enumerated(EnumType.STRING)
    private ProjectRisk risk;

    @NotNull(message = "Project's status cannot be null")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @NotNull(message = "Project's member cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;
    
    @Transient
    private Boolean canBeDeleted; 

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

	public LocalDateTime getInitDate() {
		return initDate;
	}

	public void setInitDate(LocalDateTime initDate) {
		this.initDate = initDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public LocalDateTime getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(LocalDateTime realEndDate) {
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