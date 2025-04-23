package com.githubtracker.organizationtrack.model;


import lombok.Data;

@Data
public class Organization {
    private String login;
    private String name;
    private String description;
    private String url;
    // Add other relevant organization details
}