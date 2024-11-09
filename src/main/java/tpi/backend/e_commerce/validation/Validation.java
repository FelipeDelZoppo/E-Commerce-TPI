package tpi.backend.e_commerce.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.enums.Role;
import tpi.backend.e_commerce.services.JwtService.JwtService;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

@Component
public class Validation {
    
    @Autowired
    private JwtService jwtService;
    
    public ResponseEntity<Map<String,String>> validate(BindingResult result){
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);    
    }

    public ResponseEntity<Map<String,String>> validate(String field, String message, int status){
        return ResponseEntity.status(status).body(Collections.singletonMap(field, message));
    }

    public void validateRole(String jwt)throws Exception {
        Role role = jwtService.extractRole(jwt);
        if (!role.equals(Role.ADMIN)) {
            throw new RuntimeException("Solo los usuarios con rol ADMIN pueden registrar otro usuario ADMIN");
        }
    }
    
}
