package com.helloegor03.common.dto;

public class EmployeeCreatedEvent {
    private Long projectId;
    private String username;
    private Employee employee;

    public EmployeeCreatedEvent(Long projectId, String username, Employee employee) {
        this.projectId = projectId;
        this.username = username;
        this.employee = employee;
    }

    public EmployeeCreatedEvent() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
