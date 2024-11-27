package tpi.backend.e_commerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.backend.e_commerce.services.gitHub.interfaces.IGitHubService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/github")
public class GitHubController {
    
    @Autowired
    private IGitHubService gitHubService;

    @GetMapping("/issues")
    public ResponseEntity<Object> getIssues() {
        return gitHubService.getOrganizationIssues();
    }
}
