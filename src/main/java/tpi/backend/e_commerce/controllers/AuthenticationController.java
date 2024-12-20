package tpi.backend.e_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpi.backend.e_commerce.dto.auth.request.SignInRequest;
import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;

import tpi.backend.e_commerce.services.JwtService.interfaces.IAuthenticationService;
import tpi.backend.e_commerce.services.user.interfaces.ISaveUserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private ISaveUserService saveService;

    //Endpoint para registrar un usuario
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
        @Valid @RequestBody SignUpRequest request, BindingResult result, @RequestHeader(value = "Authorization", required = false) String authorization
        ) {
        return authenticationService.signup(request,result,authorization);
    }   

    //Endpoint para autenticar al usuario en el inicio de sesion
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SignInRequest request , BindingResult result) {
        return authenticationService.signin(request,result);
    }

    //Este endpoint sera utilizado para registrar el primer ADMIN en la BD (hardcodeo)
    @PostMapping("/firstAdmin")
    public ResponseEntity<?> signUpAdmin(@Valid @RequestBody SignUpRequest userDto, BindingResult result){
        return saveService.signUpAdmin(userDto, result);
    }
}
