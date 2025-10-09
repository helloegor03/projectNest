package com.helloegor03.common.dto;

public class EmployeeCreatedEvent {
    private Long projectId;
    private Employee employee;
    private Role role = Role.ROLE_EMPLOYEE;

    public EmployeeCreatedEvent(Long projectId, Employee employee, Role role) {
        this.projectId = projectId;
        this.employee = employee;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
