package com.helloegor03.task.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;
    private String name;
    private boolean isCompleted;
    private Long UserIdForEmployee;




}
