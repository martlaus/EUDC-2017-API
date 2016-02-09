package eudcApi.rest.filter;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

public class EudcApiSecurityContext implements SecurityContext {

    private UriInfo uriInfo;
    private EudcApiPrincipal EudcApiPrincipal;

    public EudcApiSecurityContext(EudcApiPrincipal principal, UriInfo uriInfo) {
        this.uriInfo = uriInfo;
        this.EudcApiPrincipal = principal;
    }

    @Override
    public Principal getUserPrincipal() {
        return EudcApiPrincipal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return EudcApiPrincipal != null && EudcApiPrincipal.isUserInRole(role);
    }

    @Override
    public boolean isSecure() {
        return "https".equals(uriInfo.getRequestUri().getScheme());
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.CLIENT_CERT_AUTH;
    }
}
