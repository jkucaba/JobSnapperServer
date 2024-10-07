package jk.jobsnapper.service;

import jk.jobsnapper.entity.User;
import jk.jobsnapper.models.LoginRequest;
import jk.jobsnapper.repository.UserRepository;
import jk.jobsnapper.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthenticationService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> authenticate(LoginRequest loginRequest) {
        System.out.println("Authenticating user with email: " + loginRequest.getEmail() + " and password: " + loginRequest.getPassword());
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(loginRequest.getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}