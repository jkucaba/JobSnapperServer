package jk.jobsnapper.util;

import io.jsonwebtoken.*;
import jk.jobsnapper.entity.User;
import jk.jobsnapper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Component
public class JwtUtil {

    private final KeyPair keyPair;

    @Autowired
    private UserRepository userRepository;

    public JwtUtil(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String generateToken(String email) {
        User user = userRepository.findByEmail(email);
        return Jwts.builder()
                .setSubject(email)
                .claim("iduser", user.getIduser())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .claim("email", user.getEmail())
                .claim("birthday", user.getBirthday())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(keyPair.getPrivate()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(keyPair.getPrivate())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}