package com.githubtracker.organizationtrack.model;


import lombok.Data;

@Data
public class Contributor {
    private String login;
    private String html_url;
    private int contributions;
    // Add other relevant contributor details
}
