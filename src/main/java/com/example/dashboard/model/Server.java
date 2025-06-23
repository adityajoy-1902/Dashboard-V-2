package com.example.dashboard.model;

import java.util.List;

public class Server {
    private String name;
    private String ip;
    private String os;
    private List<Service> services;

    // Default constructor
    public Server() {
    }

    // Constructor with all fields
    public Server(String name, String ip, String os, List<Service> services) {
        this.name = name;
        this.ip = ip;
        this.os = os;
        this.services = services;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getOs() {
        return os;
    }

    public List<Service> getServices() {
        return services;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Server{" +
                "name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", os='" + os + '\'' +
                ", services=" + services +
                '}';
    }
} 