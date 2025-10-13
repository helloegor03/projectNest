package com.helloegor03.task.controller;

import com.helloegor03.task.dto.CreateTaskRequest;
import com.helloegor03.task.model.Task;
import com.helloegor03.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody CreateTaskRequest request,
                           @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return taskService.createTask(jwtToken, request.getProjectId(), request.getUserId(), request.getName());
    }

    @PostMapping("/{taskId}/complete")
    public String completeTask(@PathVariable Long taskId,
                               @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        taskService.completeTask(jwtToken, taskId);
        return "Task completed successfully";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable Long taskId,
                             @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        taskService.deleteTask(jwtToken, taskId);
        return "Task deleted successfully";
    }

    @GetMapping("/my")
    public Optional<List<Task>> getMyTasks(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return taskService.getTasksByUserId(jwtToken);
    }
}
