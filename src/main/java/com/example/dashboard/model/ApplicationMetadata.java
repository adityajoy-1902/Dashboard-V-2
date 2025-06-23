package com.example.dashboard.model;

public class ApplicationMetadata {
    private String name;
    private String file;
    private String displayName;

    // Default constructor
    public ApplicationMetadata() {
    }

    // Constructor with all fields
    public ApplicationMetadata(String name, String file, String displayName) {
        this.name = name;
        this.file = file;
        this.displayName = displayName;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "ApplicationMetadata{" +
                "name='" + name + '\'' +
                ", file='" + file + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
} 