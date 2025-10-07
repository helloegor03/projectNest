package com.helloegor03.project.controller;

import com.helloegor03.project.dto.AddEmployeeRequest;
import com.helloegor03.project.dto.CreateProjectRequest;
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

    @PostMapping("create")
    public Project createProject(@RequestBody CreateProjectRequest request,
                                 @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return projectService.createProject(request.getName(), jwtToken);
    }

    @PostMapping("add/employee")
    public Project addEmployee(@RequestBody AddEmployeeRequest request,
                               @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return projectService.addEmployeeToProject(request.getProjectId(), request.getUserId(), jwtToken);
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
