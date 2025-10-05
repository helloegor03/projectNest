package com.helloegor03.project.service;

import com.helloegor03.project.config.EmployeeClient;
import com.helloegor03.project.config.JwtUtil;
import com.helloegor03.project.dto.UserResponse;
import com.helloegor03.project.model.Employee;
import com.helloegor03.project.model.Project;
import com.helloegor03.project.model.Role;
import com.helloegor03.project.repository.ProjectRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeClient employeeClient;
    private final JwtUtil jwtUtil;
    private final KafkaTemplate<String, ProjectCreatedEvent> kafkaTemplate;

    public ProjectService(ProjectRepository projectRepository,
                          EmployeeClient employeeClient,
                          JwtUtil jwtUtil,
                          KafkaTemplate<String, ProjectCreatedEvent> kafkaTemplate) {
        this.projectRepository = projectRepository;
        this.employeeClient = employeeClient;
        this.jwtUtil = jwtUtil;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Project createProject(String projectName, String jwtToken) {
        if (!jwtUtil.validateJwtToken(jwtToken)) {
            throw new RuntimeException("Invalid JWT token");
        }
        Long userId = jwtUtil.getUserIdFromToken(jwtToken);

        Project project = new Project();
        project.setName(projectName);
        project.setOwnerId(userId);
        UserResponse userResponse = employeeClient.findUserById(userId);

        Employee chief = new Employee();
        chief.setUserId(userResponse.getId());
        chief.setUsername(userResponse.getUsername());
        chief.setRole(Role.ROLE_CHIEF);

        project.setEmployees(new ArrayList<>());
        project.getEmployees().add(chief);

        Project saved = projectRepository.save(project);
        ProjectCreatedEvent event = new ProjectCreatedEvent(
                saved.getId(),
                saved.getEmployees(),
                saved.getName()
        );
        kafkaTemplate.send("project-created-topic", event);

        return saved;
    }

    public Project addEmployeeToProject(Long projectId, Long userId, String token) {
        if (!jwtUtil.validateJwtToken(token)) {
            throw new RuntimeException("Invalid JWT token");
        }
        Long chiefUserId = jwtUtil.getUserIdFromToken(token);

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

        Project saved = projectRepository.save(project);

        ProjectCreatedEvent event = new ProjectCreatedEvent(saved.getId(), saved.getEmployees(), saved.getName());

        return saved;
    }

    public List<Employee> getEmployeesByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return project.getEmployees();
    }

    public Employee getEmployeeById(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return project.getEmployees().stream()
                .filter(emp -> emp.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found in this project"));
    }
}
