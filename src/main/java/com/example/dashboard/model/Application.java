package com.example.dashboard.model;

import java.util.List;

public class Application {
    private String name;
    private List<Environment> environments;

    // Default constructor
    public Application() {
    }

    // Constructor with all fields
    public Application(String name, List<Environment> environments) {
        this.name = name;
        this.environments = environments;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Environment> getEnvironments() {
        return environments;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEnvironments(List<Environment> environments) {
        this.environments = environments;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", environments=" + environments +
                '}';
    }
} 