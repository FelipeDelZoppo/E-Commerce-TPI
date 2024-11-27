package tpi.backend.e_commerce.repositories;

import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.Issue;

public interface IGitHubRepositories extends CrudRepository<Issue, Long> {
    
}
