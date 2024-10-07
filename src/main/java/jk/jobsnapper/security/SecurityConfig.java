
package jk.jobsnapper.security;

import jk.jobsnapper.manager.RoleBasedAuthorizationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                /*.authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );*/
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/publicKey")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).access(RoleBasedAuthorizationManager.hasRole("admin")) // tylko użytkownicy z rolą ADMIN mają dostęp do endpointów /admin/**
                        .requestMatchers(new AntPathRequestMatcher("/user/**")).access(RoleBasedAuthorizationManager.hasRole("pracownik")) // tylko użytkownicy z rolą USER mają dostęp do endpointów /user/**
                        .requestMatchers(new AntPathRequestMatcher("/employer/**")).access(RoleBasedAuthorizationManager.hasRole("employer")) // tylko użytkownicy z rolą PRACODAWCA mają dostęp do endpointów /employer/**
                        .anyRequest().authenticated() // wszystkie inne żądania wymagają uwierzytelnienia
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // Allows all origins, adjust this as necessary
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
