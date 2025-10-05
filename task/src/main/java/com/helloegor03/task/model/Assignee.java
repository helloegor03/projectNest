package com.helloegor03.task.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assignee")
public class Assignee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private Long userIdForEmployee;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserIdForEmployee() {
        return userIdForEmployee;
    }

    public void setUserIdForEmployee(Long userIdForEmployee) {
        this.userIdForEmployee = userIdForEmployee;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
