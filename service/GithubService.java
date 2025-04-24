package com.githubtracker.organizationtrack.service;

import com.githubtracker.organizationtrack.model.Organization;
import com.githubtracker.organizationtrack.model.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GithubService {

    private final String GITHUB_API_URL = "https://api.github.com";
    private final RestTemplate restTemplate = new RestTemplate();

    public Organization fetchOrganizationDetails(String orgName) {
        String url = String.format("%s/orgs/%s", GITHUB_API_URL, orgName);
        try {
            return restTemplate.getForObject(url, Organization.class);
        } catch (Exception e) {
            // Handle exceptions (e.g., organization not found)
            return null;
        }
    }

    public List<Repository> fetchOrganizationRepositories(String orgName) {
        String url = String.format("%s/orgs/%s/repos", GITHUB_API_URL, orgName);
        try {
            Repository[] repositories = restTemplate.getForObject(url, Repository[].class);
            return List.of(repositories);
        } catch (Exception e) {
            // Handle exceptions
            return List.of();
        }
    }

    public Map<String, Integer> fetchOrganizationStats(String orgName) {
        String orgDetailsUrl = String.format("%s/orgs/%s", GITHUB_API_URL, orgName);
        String reposUrl = String.format("%s/orgs/%s/repos", GITHUB_API_URL, orgName);
        String membersUrl = String.format("%s/orgs/%s/public_members", GITHUB_API_URL, orgName); // Or /members for all

        Map<String, Integer> stats = new HashMap<>();
        try {
            Organization organization = restTemplate.getForObject(orgDetailsUrl, Organization.class);
            if (organization != null) {
                stats.put("publicRepos", organization.getPublic_repos()); // Assuming 'public_repos' field exists
            }

            Repository[] repositories = restTemplate.getForObject(reposUrl, Repository[].class);
            if (repositories != null) {
                stats.put("totalRepos", repositories.length);
            }

            // Fetching members might require pagination and authentication for private members
            // For now, let's just try to get public members count
            ResponseEntity<List> membersResponse = restTemplate.getForEntity(membersUrl, List.class);
            if (membersResponse.getBody() != null) {
                stats.put("members", membersResponse.getBody().size());
            }

            return stats;

        } catch (Exception e) {
            // Handle exceptions
            return null;
        }
    }

    // You'll need to implement logic to store and retrieve recent organizations
    // This could involve a database, in-memory storage, etc.
    // For now, the controller directly returns hardcoded data for /recent-orgs
}