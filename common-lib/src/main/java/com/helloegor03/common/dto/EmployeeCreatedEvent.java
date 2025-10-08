package com.helloegor03.common.dto;

public class EmployeeCreatedEvent {
    private Long projectId;
    private Employee employee;

    public EmployeeCreatedEvent(Long projectId, Employee employee) {
        this.projectId = projectId;
        this.employee = employee;
    }

    public EmployeeCreatedEvent() {
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
