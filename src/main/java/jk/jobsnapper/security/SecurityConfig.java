
package jk.jobsnapper.security;

import jk.jobsnapper.manager.RoleBasedAuthorizationManager;
import jk.jobsnapper.util.JwtAuthenticationFilter;
import jk.jobsnapper.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtUtil jwtUtil;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtil);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
/*                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );*/
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/publicKey")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).access(RoleBasedAuthorizationManager.hasRole("admin"))
                        .requestMatchers(new AntPathRequestMatcher("/user/**")).access(RoleBasedAuthorizationManager.hasRole("pracownik"))
                        .requestMatchers(new AntPathRequestMatcher("/employer/**")).access(RoleBasedAuthorizationManager.hasRole("employer"))
                        .anyRequest().authenticated()
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
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
