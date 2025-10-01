package com.helloegor03.project.service;

import com.helloegor03.project.config.CustomJwtUtil;
import com.helloegor03.project.config.EmployeeClient;
import com.helloegor03.project.dto.UserResponse;
import com.helloegor03.project.model.Employee;
import com.helloegor03.project.model.Project;
import com.helloegor03.project.model.Role;
import com.helloegor03.project.repository.EmployeeRepository;
import com.helloegor03.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeClient employeeClient;

    public ProjectService(ProjectRepository projectRepository, EmployeeClient employeeClient) {
        this.projectRepository = projectRepository;
        this.employeeClient = employeeClient;
    }

    public Project createProject(String projectName, String jwtToken, CustomJwtUtil jwtUtil) {
        if (!jwtUtil.validateJwtToken(jwtToken)) {
            throw new RuntimeException("Invalid JWT token");
        }
        Long userId = Long.parseLong(jwtUtil.getUserIdFromToken(jwtToken));

        Project project = new Project();
        project.setName(projectName);
        project.setOwnerId(userId);
        UserResponse userResponse = employeeClient.findUserById(userId);

        Employee chief = new Employee();
        chief.setUserId(userResponse.getId());
        chief.setUsername(userResponse.getUsername());
        chief.setRole(Role.ROLE_CHIEF); //по стандарту пользователь который создаёт новый проект
                                        //получает роль шефа

        project.setEmployees(new ArrayList<>());
        project.getEmployees().add(chief);

        return projectRepository.save(project);
    }

    public Project addEmployeeToProject(Long projectId, Long userId, String jwtToken, CustomJwtUtil jwtUtil){
        if (!jwtUtil.validateJwtToken(jwtToken)) {
            throw new RuntimeException("Invalid JWT token");
        }
        Long chiefUserId = Long.parseLong(jwtUtil.getUserIdFromToken(jwtToken));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getOwnerId().equals(chiefUserId)) {
            throw new RuntimeException("This is not your project");
        }

        UserResponse userResponse = employeeClient.findUserById(userId);

        Employee newEmployee = new Employee();
        newEmployee.setUserId(userResponse.getId());
        newEmployee.setUsername(userResponse.getUsername());
        newEmployee.setRole(Role.ROLE_EMPLOYEE);

        project.getEmployees().add(newEmployee);

        return projectRepository.save(project);
    }


}
