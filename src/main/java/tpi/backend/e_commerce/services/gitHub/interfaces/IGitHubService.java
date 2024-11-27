package tpi.backend.e_commerce.services.gitHub.interfaces;


import org.springframework.http.ResponseEntity;

public interface IGitHubService {
    
    ResponseEntity<Object> getOrganizationIssues();
}
