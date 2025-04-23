package com.githubtracker.organizationtrack.controller;

import com.githubtracker.organizationtrack.model.Organization;
import com.githubtracker.organizationtrack.model.Repository;
import com.githubtracker.organizationtrack.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
public class GithubApiController {

    @Autowired
    private GithubService githubService;

    @GetMapping("/org/{orgName}")
    public ResponseEntity<Organization> getOrganizationDetails(@PathVariable String orgName) {
        Organization organization = githubService.fetchOrganizationDetails(orgName);
        if (organization != null) {
            return ResponseEntity.ok(organization);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/org/{orgName}/repos")
    public ResponseEntity<List<Repository>> getOrganizationRepositories(@PathVariable String orgName) {
        List<Repository> repositories = githubService.fetchOrganizationRepositories(orgName);
        return ResponseEntity.ok(repositories);
    }

    @GetMapping("/org/{orgName}/stats")
    public ResponseEntity<Map<String, Integer>> getOrganizationStats(@PathVariable String orgName) {
        Map<String, Integer> stats = githubService.fetchOrganizationStats(orgName);
        if (stats != null && !stats.isEmpty()) {
            return ResponseEntity.ok(stats);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to fetch recent organizations (you'll need to decide how to store these)
    // For now, let's return some hardcoded data for demonstration
    @GetMapping("/recent-orgs")
    public ResponseEntity<List<Map<String, Object>>> getRecentOrganizations() {
        List<Map<String, Object>> recentOrgs = List.of(
                Map.of("login", "ExampleOrg", "totalRepos", 12, "members", 8),
                Map.of("login", "AcmeCorp", "totalRepos", 24, "members", 15)
        );
        return ResponseEntity.ok(recentOrgs);
    }
}