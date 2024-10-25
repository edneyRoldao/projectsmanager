package com.edney.projectsmanager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edney.projectsmanager.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	String DELETE_PROJECT_SQL = "UPDATE projects p SET deleted = true WHERE p.id = :id";
	
	List<Project> findAllByDeletedFalse();

	@Modifying
	@Query(value = DELETE_PROJECT_SQL, nativeQuery = true)
	int logicalDeletion(@Param("id") Long id);
	
	Optional<Project> findFirstByIdAndDeletedFalse(Long id);
		
	
}
