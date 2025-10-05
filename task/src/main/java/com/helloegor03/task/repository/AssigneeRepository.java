package com.helloegor03.task.repository;

import com.helloegor03.task.model.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
}
