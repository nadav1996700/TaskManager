package com.example.taskmanager.model;

public class TimedTask extends Task {
    private int duration;

    public TimedTask(String name, int duration) {
        super(name);
        this.duration = duration;
    }

    @Override
    public String getDescription() {
        return "Timed Task: " + getName() + ", Duration: " + duration + " minutes";
    }
}
