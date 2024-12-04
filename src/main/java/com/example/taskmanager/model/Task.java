package com.example.taskmanager.model;

public class Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getDescription() {
        return "Task: " + name;
    }

    public String getName() {
        return name;
    }
}
