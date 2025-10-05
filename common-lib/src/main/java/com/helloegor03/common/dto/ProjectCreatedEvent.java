package com.helloegor03.common.dto;

import java.util.List;

public class ProjectCreatedEvent {
    private Long projectId;
    private String name;
    private List<Employee> employees;

    public ProjectCreatedEvent() {}

    public ProjectCreatedEvent(Long projectId, String name, List<Employee> employees) {
        this.projectId = projectId;
        this.name = name;
        this.employees = employees;
    }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
}