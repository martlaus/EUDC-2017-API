package eudcApi.rest.filter;

import eudcApi.model.AuthenticatedUser;
import eudcApi.service.AuthenticatedUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static eudcApi.guice.GuiceInjector.getInjector;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    private static final int HTTP_AUTHENTICATION_TIMEOUT = 419;
    private Logger logger = LoggerFactory.getLogger(getClass());


    private UriInfo uriInfo;
    private HttpServletRequest request;

    public SecurityFilter(@Context UriInfo uriInfo, @Context HttpServletRequest request) {
        this.uriInfo = uriInfo;
        this.request = request;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = request.getHeader("Token");

        if (token != null) {
            AuthenticatedUserService authenticatedUserService = newAuthenticatedUserService();
            AuthenticatedUser authenticatedUser = authenticatedUserService.getAuthenticatedUserByToken(token);
            if (authenticatedUser != null && isCorrectUser(authenticatedUser)) {
                EudcApiPrincipal principal = new EudcApiPrincipal(authenticatedUser);
                EudcApiSecurityContext securityContext = new EudcApiSecurityContext(principal, uriInfo);
                requestContext.setSecurityContext(securityContext);
            } else {
                logger.info("Access denied:" + requestContext.toString());
                Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Denied")
                        .type(MediaType.APPLICATION_JSON).build();
            }
        } else {
            logger.info("Access denied, no token:" + requestContext.toString());

        }

    }

    protected AuthenticatedUserService newAuthenticatedUserService() {
        return getInjector().getInstance(AuthenticatedUserService.class);
    }

    private boolean isCorrectUser(AuthenticatedUser authenticatedUser) {
        return authenticatedUser.getUser().getEmail().equals(request.getHeader("Email"));
    }

}
