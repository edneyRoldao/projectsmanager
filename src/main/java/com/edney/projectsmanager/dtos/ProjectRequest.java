package com.edney.projectsmanager.dtos;

import com.edney.projectsmanager.domain.Member;
import com.edney.projectsmanager.domain.Project;
import com.edney.projectsmanager.domain.ProjectRisk;
import com.edney.projectsmanager.domain.ProjectStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

public class ProjectRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
//	@NotNull(message = "Project's memberId cannot be null")
	private DataSelectDTO<Member> member;
	
	@NotBlank(message = "Project's name cannot be empty")
	private String name;
	
    @NotBlank(message = "Project's description cannot be empty")
	private String description;
	    
//    @NotBlank(message = "Project's risk cannot be empty")
//    private DataSelectDTO<ProjectRisk> risk;
    private String risk;
    
//    @NotBlank(message = "Project's status cannot be empty")
//    private DataSelectDTO<ProjectStatus> status;
    private String status;
    
    @DecimalMin(value = "1.00", message = "Project's budget should at least 1.00")
    private BigDecimal budget;
        
    private LocalDate initDate;
    
    private LocalDate endDate;
    
    private LocalDate realEndDate;
    
    public ProjectRequest() {    	
    	
    }

    public ProjectRequest(Project project) {
    	BeanUtils.copyProperties(project, this);
    	this.risk = project.getRisk().name();    	
    	this.status = project.getStatus().name();
    	this.member = new DataSelectDTO<>(project.getMember(), true);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DataSelectDTO<Member> getMember() {
		return member;
	}

	public void setMember(DataSelectDTO<Member> member) {
		this.member = member;
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

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
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

	@Override
	public String toString() {
		return "ProjectRequest [id=" + id + ", member=" + member + ", name=" + name + ", description=" + description
				+ ", risk=" + risk + ", status=" + status + ", budget=" + budget + ", initDate=" + initDate
				+ ", endDate=" + endDate + ", realEndDate=" + realEndDate + "]";
	}
    	
}
