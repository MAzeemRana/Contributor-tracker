package com.githubtracker.organizationtrack.repository;



// You might not need a Spring Data JPA repository for fetching directly from the GitHub API.
// This interface could be used if you were planning to store organization data in your own database.
// For now, the logic for fetching is in the GithubService.

public interface OrganizationRepository {
    // You can define methods here if you decide to persist organization data.
}