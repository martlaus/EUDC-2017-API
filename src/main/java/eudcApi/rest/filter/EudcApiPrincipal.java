package eudcApi.rest.filter;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.User;

import java.security.Principal;

public class EudcApiPrincipal implements Principal {

    private AuthenticatedUser authenticatedUser;

    public EudcApiPrincipal(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public String getName() {
        return getUser().getEmail();
    }

    public User getUser() {
        return authenticatedUser.getUser();
    }

    public String getSecurityToken() {
        return authenticatedUser.getToken();
    }

    public boolean isUserInRole(String role) {
        return authenticatedUser != null && authenticatedUser.getUser().getRole().toString().equals(role);
    }

    public AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }
}
