package com.helloegor03.task.repository;

import com.helloegor03.task.model.Assignee;
import com.helloegor03.task.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
    boolean existsByProjectId(Long projectId);

    Optional<Assignee> findByProjectIdAndUserId(Long projectId, Long userId);

    boolean existsByProjectIdAndUserIdAndRole(Long projectId, Long userId, Role role);
}
