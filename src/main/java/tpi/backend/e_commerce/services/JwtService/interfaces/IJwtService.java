package tpi.backend.e_commerce.services.JwtService.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import tpi.backend.e_commerce.enums.Role;

public interface IJwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails, Role role);

    boolean isTokenValid(String token, UserDetails userDetails);
}

