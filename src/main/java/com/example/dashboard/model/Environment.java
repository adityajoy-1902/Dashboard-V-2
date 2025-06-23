package com.example.dashboard.model;

import java.util.List;

public class Environment {
    private String name;
    private List<Server> servers;

    // Default constructor
    public Environment() {
    }

    // Constructor with all fields
    public Environment(String name, List<Server> servers) {
        this.name = name;
        this.servers = servers;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Server> getServers() {
        return servers;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Environment{" +
                "name='" + name + '\'' +
                ", servers=" + servers +
                '}';
    }
}