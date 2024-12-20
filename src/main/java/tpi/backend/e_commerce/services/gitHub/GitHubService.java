package tpi.backend.e_commerce.services.gitHub;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tpi.backend.e_commerce.mapper.GitHubMapper;
import tpi.backend.e_commerce.models.Issue;
import tpi.backend.e_commerce.repositories.IGitHubRepositories;
import tpi.backend.e_commerce.services.gitHub.interfaces.IGitHubService;

@Service
public class GitHubService implements IGitHubService {
    
    @Autowired
    private IGitHubRepositories gitHubRepositories;

    @Value("${github.api.token}")
    private String githubToken;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<Object> getOrganizationIssues() {
        String baseUrl = "https://api.github.com/repos/Grupo-9-Anita/e-commerce-tpi-backend/issues";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + githubToken);
        headers.set("Accept", "application/vnd.github+json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<Issue> allIssues = new ArrayList<>();
        int page = 1;
        boolean hasMore = true;

        while (hasMore) {
            // Construir la URL con parámetros de paginación
            String url = String.format("%s?state=all&per_page=100&page=%d", baseUrl, page);
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {}
            );

            // Obtener el cuerpo de la respuesta
            List<Map<String, Object>> issues = response.getBody();
            if (issues == null || issues.isEmpty()) {
                hasMore = false; // Detener el bucle si no hay más resultados
            } else {
                // Mapear las issues y agregarlas a la lista final
                allIssues.addAll(GitHubMapper.toEntities(issues));
                page++; // Pasar a la siguiente página
            }
        }

        String baseUrlFront = "https://api.github.com/repos/Grupo-9-Anita/e-commerce-tpi-frontend/issues";
        headers.set("Authorization", "Bearer " + githubToken);
        headers.set("Accept", "application/vnd.github+json");

       
        page = 1;
        hasMore = true;

        while (hasMore) {
            // Construir la URL con parámetros de paginación
            String url = String.format("%s?state=all&per_page=100&page=%d", baseUrlFront, page);
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {}
            );

            // Obtener el cuerpo de la respuesta
            List<Map<String, Object>> issues = response.getBody();
            if (issues == null || issues.isEmpty()) {
                hasMore = false; // Detener el bucle si no hay más resultados
            } else {
                // Mapear las issues y agregarlas a la lista final
                allIssues.addAll(GitHubMapper.toEntities(issues));
                page++; // Pasar a la siguiente página
            }
        }
        // Guardar todas las issues en la base de datos
        allIssues.forEach(gitHubRepositories::save);

        return ResponseEntity.ok().build();
    }

}
