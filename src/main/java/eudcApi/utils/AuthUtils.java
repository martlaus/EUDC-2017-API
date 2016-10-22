package eudcApi.utils;

import eudcApi.model.AuthenticatedUser;
import eudcApi.rest.filter.EudcApiPrincipal;
import eudcApi.service.CardService;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by toose on 22.10.16.
 */
public class AuthUtils {
    @Inject
    private CardService cardService;

//    private SecurityContext securityContext;


    public AuthenticatedUser getAuthUser(SecurityContext securityContext) {
        EudcApiPrincipal EudcApiPrincipal = (EudcApiPrincipal) securityContext.getUserPrincipal();
        if (EudcApiPrincipal != null) {
            return EudcApiPrincipal.getAuthenticatedUser();
        }
        return null;
    }

    public boolean isUserRoleUser(SecurityContext securityContext) {
        AuthenticatedUser authenticatedUser = getAuthUser(securityContext);
        return isUserAuthenticated(authenticatedUser) && authenticatedUser.getUser().getRole().equals("USER");
    }

    public boolean isUserRoleAdmin(SecurityContext securityContext) {
        AuthenticatedUser authenticatedUser = getAuthUser(securityContext);
        return isUserAuthenticated(authenticatedUser) && authenticatedUser.getUser().getRole().equals("ADMIN");
    }

    public boolean isUserAuthenticated(AuthenticatedUser authenticatedUser) {
        return authenticatedUser != null;
    }

}
