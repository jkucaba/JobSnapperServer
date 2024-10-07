package jk.jobsnapper.manager;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

public class RoleBasedAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final String role;

    public RoleBasedAuthorizationManager(String role) {
        this.role = role;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        Authentication authentication = authenticationSupplier.get();
        return new AuthorizationDecision(authentication.getAuthorities().contains(new SimpleGrantedAuthority(role)));
    }

    public static AuthorizationManager<RequestAuthorizationContext> hasRole(String role) {
        return new RoleBasedAuthorizationManager(role);
    }
}