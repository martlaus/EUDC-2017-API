package eudcApi.rest;

import eudcApi.rest.filter.EudcApiPrincipal;
import eudcApi.service.AuthenticatedUserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by mart on 25.11.15.
 */
@Path("signout")
public class LogoutResource {

    @Inject
    private AuthenticatedUserService authenticatedUserService;

    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @POST
    @RolesAllowed({"USER", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public void logOut() throws Exception {
        EudcApiPrincipal EudcApiPrincipal = (EudcApiPrincipal) securityContext.getUserPrincipal();
        authenticatedUserService.deleteAuthenticatedUser(EudcApiPrincipal.getAuthenticatedUser());
    }
}
