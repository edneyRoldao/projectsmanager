package com.edney.projectsmanager.dtos;

import com.edney.projectsmanager.domain.Project;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProjectRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message = "Project's memberId cannot be null")
	private Long memberId;
	
	@NotBlank(message = "Project's name cannot be empty")
	private String name;
	
    @NotBlank(message = "Project's description cannot be empty")
	private String description;
	    
    @NotBlank(message = "Project's risk cannot be empty")
    private String risk;
    
    @NotBlank(message = "Project's status cannot be empty")
    private String status;

    @NotBlank(message = "Project's budget cannot be empty")
    private String budget;
        
    private LocalDate initDate;
    
    private LocalDate endDate;
    
    private LocalDate realEndDate;

    public ProjectRequest(Project project) {
    	BeanUtils.copyProperties(project, this);
    	this.risk = project.getRisk().name();    	
    	this.status = project.getStatus().name();
    	this.memberId = project.getMember().getId();
    	this.budget = project.getBudget().toString();
    }

}
