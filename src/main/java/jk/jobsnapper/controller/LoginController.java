package jk.jobsnapper.controller;

import jk.jobsnapper.models.LoginRequest;
import jk.jobsnapper.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Received login request: " + loginRequest);
        ResponseEntity<?> response = authenticationService.authenticate(loginRequest);
        System.out.println("Sending response: " + response);
        return response;
    }
}