package jk.jobsnapper.manager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RoleBasedAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final String role;

    public RoleBasedAuthorizationManager(String role) {
        this.role = role;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        Authentication authentication = authenticationSupplier.get();
        System.out.println("User authorities: " + authentication.getAuthorities());
        System.out.println("Role: " + role);
        return new AuthorizationDecision(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_" + role)));
    }

    public static AuthorizationManager<RequestAuthorizationContext> hasRole(String role) {
        return new RoleBasedAuthorizationManager(role);
    }

}