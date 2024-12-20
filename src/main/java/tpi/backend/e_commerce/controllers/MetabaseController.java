package tpi.backend.e_commerce.controllers;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

@RestController
@RequestMapping("/metabase")
@CrossOrigin(origins = "http://localhost:5173")
public class MetabaseController {

    private static final String METABASE_SITE_URL = "http://localhost:3000";
    private static final String METABASE_SECRET_KEY = "c4be277bb6abbeee3761c9762da0e48b4fbfdaed313657fa2fd20ef7281a2344";

    @GetMapping("/dashboard-url")
    private Map<String, String> getDashboardUrl(){
        // Crear una clave segura a partir del secret key
        SecretKey key = Keys.hmacShaKeyFor(METABASE_SECRET_KEY.getBytes());

        // Crear el payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("resource", Map.of("dashboard", 3)); // ID del dashboard
        payload.put("params", new HashMap<>()); // Parámetros opcionales
        payload.put("exp", new Date(System.currentTimeMillis() + 10 * 60 * 1000)); // Expira en 10 minutos

        // Firmar el token
        String token = Jwts.builder()
                .setClaims(payload)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Construir la URL del iframe
        String iframeUrl = METABASE_SITE_URL + "/embed/dashboard/" + token + "#bordered=true&titled=true";

        // Retornar la URL como JSON
        Map<String, String> response = new HashMap<>();
        response.put("iframeUrl", iframeUrl);
        return response;
    } 
    

    @GetMapping("/dashboard-sales-url")
    public Map<String, String> getSalesDashboardUrl() {
        // Crear una clave segura a partir del secret key
        SecretKey key = Keys.hmacShaKeyFor(METABASE_SECRET_KEY.getBytes());

        // Crear el payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("resource", Map.of("dashboard", 4)); // ID del dashboard
        payload.put("params", new HashMap<>()); // Parámetros opcionales
        payload.put("exp", new Date(System.currentTimeMillis() + 10 * 60 * 1000)); // Expira en 10 minutos

        // Firmar el token
        String token = Jwts.builder()
                .setClaims(payload)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Construir la URL del iframe
        String iframeUrl = METABASE_SITE_URL + "/embed/dashboard/" + token + "#bordered=true&titled=true";

        // Retornar la URL como JSON
        Map<String, String> response = new HashMap<>();
        response.put("iframeUrl", iframeUrl);
        return response; 
    }

    @GetMapping("/dashboard-clients-url")
    public Map<String, String> getClientsDashboardUrl() {
        // Crear una clave segura a partir del secret key
        SecretKey key = Keys.hmacShaKeyFor(METABASE_SECRET_KEY.getBytes());

        // Crear el payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("resource", Map.of("dashboard", 5)); // ID del dashboard
        payload.put("params", new HashMap<>()); // Parámetros opcionales
        payload.put("exp", new Date(System.currentTimeMillis() + 10 * 60 * 1000)); // Expira en 10 minutos

        // Firmar el token
        String token = Jwts.builder()
                .setClaims(payload)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Construir la URL del iframe
        String iframeUrl = METABASE_SITE_URL + "/embed/dashboard/" + token + "#bordered=true&titled=true";

        // Retornar la URL como JSON
        Map<String, String> response = new HashMap<>();
        response.put("iframeUrl", iframeUrl);
        return response;
    }
}
