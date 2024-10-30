package tpi.backend.e_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpi.backend.e_commerce.dto.auth.request.SignInRequest;
import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;

import tpi.backend.e_commerce.services.JwtService.interfaces.IAuthenticationService;
import tpi.backend.e_commerce.services.JwtService.interfaces.IDeleteUserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IDeleteUserService deleteService;

    //Endpoint para registrar un usuario
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest request, BindingResult result) {
        return authenticationService.signup(request,result);
    }   

    //Endpoint para autenticar al usuario en el inicio de sesion
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SignInRequest request , BindingResult result) {
        return authenticationService.signin(request,result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return deleteService.delete(id);
    }

    @PostMapping("/recover/{id}")
    public ResponseEntity<?> recover(@PathVariable Long id){
        return deleteService.recover(id);
    }
}
