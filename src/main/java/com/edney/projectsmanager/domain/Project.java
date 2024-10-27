package com.edney.projectsmanager.domain;

import com.edney.projectsmanager.dtos.ProjectRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Serial
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
    private Boolean deleted = false;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    private ProjectRisk risk;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    public Project(ProjectRequest request) {
		BeanUtils.copyProperties(request, this);
		this.risk = ProjectRisk.valueOf(request.getRisk());
		this.status = ProjectStatus.valueOf(request.getStatus());
		this.member = new Member(request.getMemberId());
		this.budget = budgetStringToDecimal(request.getBudget());
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

	private BigDecimal budgetStringToDecimal(String budget) {		
		if (budget.contains(",")) {
			budget = budget
					.replaceAll("\\.", "")
					.replace(",", ".")
					.replace("R$", "")
					.trim();
			
		}
		return new BigDecimal(budget);
	}
        
}