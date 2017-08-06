package eudcApi.rest;

import eudcApi.service.tabbie.TabbieDataServices;
import eudcApi.utils.AuthUtils;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.Map;

@Path("barcode")
public class BarcodeResource {

    @Inject
    private TabbieDataServices tabbieDataServices;

    private SecurityContext securityContext;

    private static final AuthUtils authentication = new AuthUtils();

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getBarcode(@PathParam("userId") String userId) {
        String barcode = tabbieDataServices.getUserBarcode(userId);

        Map<String, String> map = new HashMap<>();
        map.put("b64", barcode);
        return map;
    }
}
