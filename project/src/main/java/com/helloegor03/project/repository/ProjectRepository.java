package com.helloegor03.project.repository;

import com.helloegor03.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectById(Long id);
    Optional<Project> findOwnerById(Long ownerId);


}
