package com.helloegor03.task.repository;

import com.helloegor03.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
