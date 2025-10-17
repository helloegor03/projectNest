package com.helloegor03.task.service;

import com.helloegor03.common.exceptions.task.CreatorException;
import com.helloegor03.common.exceptions.task.EmployeeException;
import com.helloegor03.common.exceptions.task.ProjectNotFoundException;
import com.helloegor03.common.exceptions.task.TokenIsNotValidException;
import com.helloegor03.common.security.JwtUtil;
import com.helloegor03.task.model.Role;
import com.helloegor03.task.model.Task;
import com.helloegor03.task.repository.AssigneeRepository;
import com.helloegor03.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final AssigneeRepository assigneeRepository;
    private final TaskRepository taskRepository;
    private final JwtUtil jwtUtil;

    public TaskService(AssigneeRepository assigneeRepository, TaskRepository taskRepository, JwtUtil jwtUtil) {
        this.assigneeRepository = assigneeRepository;
        this.taskRepository = taskRepository;
        this.jwtUtil = jwtUtil;
    }

    public Task createTask(String token, Long projectId, Long userId, String name){
        if(!jwtUtil.validateJwtToken(token)){
            throw new TokenIsNotValidException("Your token is not valid");
        }
        Long chiefUserId = jwtUtil.getUserIdFromToken(token);

        if (!assigneeRepository.existsByProjectId(projectId)) {
            throw new ProjectNotFoundException("Project not found");
        }

        boolean isChief = assigneeRepository.existsByProjectIdAndUserIdAndRole(projectId, chiefUserId, Role.ROLE_CHIEF);
        if (!isChief) {
            throw new CreatorException("You are not the chief of this project");
        }

        boolean assigneeExists = assigneeRepository.findByProjectIdAndUserId(projectId, userId).isPresent();
        if (!assigneeExists) {
            throw new EmployeeException("Assignee is not part of this project");
        }

        Task task = new Task();
        task.setProjectId(projectId);
        task.setUserId(userId);
        task.setName(name);
        task.setDate(LocalDate.now());
        task.setCompleted(false);

        return taskRepository.save(task);
    }

    public void deleteTask(String token, Long projectId){
        if(!jwtUtil.validateJwtToken(token)){
            throw new TokenIsNotValidException("Token is not valid");
        }

        if(!taskRepository.findById(projectId).isPresent()){
            throw new RuntimeException("Task with this id is not found");
        }
        taskRepository.deleteById(projectId);
    }

    public Optional<List<Task>> getTasksByUserId(String token){
        if(!jwtUtil.validateJwtToken(token)){
            throw new RuntimeException("Token is not valid");
        }

        Long userId = jwtUtil.getUserIdFromToken(token);
        return taskRepository.findByUserId(userId);
    }

    public void completeTask(String token, Long taskId){
        if(!jwtUtil.validateJwtToken(token)){
            throw new RuntimeException("Token is not valid");
        }
        Long userId = jwtUtil.getUserIdFromToken(token);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUserId().equals(userId)) {
            throw new RuntimeException("You can complete only your own tasks");
        }

        task.setCompleted(true);
        taskRepository.save(task);

    }
}
