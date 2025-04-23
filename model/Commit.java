package com.githubtracker.organizationtrack.model;


import lombok.Data;

@Data
public class Commit {
    private String sha;
    private CommitDetails commit;
    private String html_url;

    @Data
    public static class CommitDetails {
        private Author author;
        private String message;
    }

    @Data
    public static class Author {
        private String name;
        private String email;
        private String date;
    }
    // Add other relevant commit details
}