package jk.jobsnapper.util;

import io.jsonwebtoken.*;
import jk.jobsnapper.entity.User;
import jk.jobsnapper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

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
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(keyPair.getPrivate())
                .parseClaimsJws(token)
                .getBody();

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + claims.get("role")));
        logger.info("Authentication: " + authorities);
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
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