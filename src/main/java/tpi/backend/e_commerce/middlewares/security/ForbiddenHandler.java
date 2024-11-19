package tpi.backend.e_commerce.middlewares.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ForbiddenHandler implements AuthenticationEntryPoint{
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException authException) throws IOException, ServletException {

        Map<String,String> errors = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        errors.put("Authorization", "Se requiere un JWT valido.");

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(errors)); 
        //Convierte el Map a JSON y se lo pasa a la response
    }
}

