package com.githubtracker.organizationtrack.model;


import lombok.Data;

@Data
public class Repository {
    private String name;
    private String full_name;
    private String description;
    private String html_url;
    // Add other relevant repository details
}