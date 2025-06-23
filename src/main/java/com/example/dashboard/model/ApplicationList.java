package com.example.dashboard.model;

import java.util.List;

public class ApplicationList {
    private List<Application> applications;

    // Default constructor
    public ApplicationList() {
    }

    // Constructor with all fields
    public ApplicationList(List<Application> applications) {
        this.applications = applications;
    }

    // Getters
    public List<Application> getApplications() {
        return applications;
    }

    // Setters
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "ApplicationList{" +
                "applications=" + applications +
                '}';
    }
} 