package com.example.dashboard.model;

import java.util.List;

public class ApplicationConfig {
    private List<ApplicationMetadata> applications;

    // Default constructor
    public ApplicationConfig() {
    }

    // Constructor with all fields
    public ApplicationConfig(List<ApplicationMetadata> applications) {
        this.applications = applications;
    }

    // Getters
    public List<ApplicationMetadata> getApplications() {
        return applications;
    }

    // Setters
    public void setApplications(List<ApplicationMetadata> applications) {
        this.applications = applications;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "ApplicationConfig{" +
                "applications=" + applications +
                '}';
    }
} 