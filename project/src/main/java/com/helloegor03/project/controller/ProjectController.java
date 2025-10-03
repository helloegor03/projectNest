package com.helloegor03.project.controller;

import com.helloegor03.project.model.Employee;
import com.helloegor03.project.model.Project;
import com.helloegor03.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public Project createProject(@RequestParam String name,
                                 @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return projectService.createProject(name, jwtToken);
    }

    @PostMapping("/{projectId}/employees/{userId}")
    public Project addEmployee(@PathVariable Long projectId,
                               @PathVariable Long userId,
                               @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return projectService.addEmployeeToProject(projectId, userId, jwtToken);
    }

    @GetMapping("/{projectId}/employees")
    public List<Employee> getEmployees(@PathVariable Long projectId) {
        return projectService.getEmployeesByProject(projectId);
    }

    @GetMapping("/{projectId}/employees/{userId}")
    public Employee getEmployee(@PathVariable Long projectId,
                                @PathVariable Long userId) {
        return projectService.getEmployeeById(projectId, userId);
    }
}
