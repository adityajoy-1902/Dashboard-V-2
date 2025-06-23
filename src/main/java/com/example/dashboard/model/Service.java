package com.example.dashboard.model;

public class Service {
    private String name;
    private String type;
    private String cmd;
    private String startScript;
    private String dbType;
    private String tnsAlias;

    // Default constructor
    public Service() {
    }

    // Constructor with all fields
    public Service(String name, String type, String cmd, String startScript, String dbType, String tnsAlias) {
        this.name = name;
        this.type = type;
        this.cmd = cmd;
        this.startScript = startScript;
        this.dbType = dbType;
        this.tnsAlias = tnsAlias;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCmd() {
        return cmd;
    }

    public String getStartScript() {
        return startScript;
    }

    public String getDbType() {
        return dbType;
    }

    public String getTnsAlias() {
        return tnsAlias;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setStartScript(String startScript) {
        this.startScript = startScript;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setTnsAlias(String tnsAlias) {
        this.tnsAlias = tnsAlias;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cmd='" + cmd + '\'' +
                ", startScript='" + startScript + '\'' +
                ", dbType='" + dbType + '\'' +
                ", tnsAlias='" + tnsAlias + '\'' +
                '}';
    }
} 