package eudcApi.rest;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.RoundLocation;
import eudcApi.rest.filter.EudcApiPrincipal;
import eudcApi.service.RoundLocationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

/**
 * Created by karl on 17.04.17.
 */
@Path("roundlocation")
public class RoundLocationResource {
	
	@Inject
    private RoundLocationService RoundLocationService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void addRoundLocation(RoundLocation roundLocation) throws Exception {
        if (roundLocation != null) {
            RoundLocationService.saveRoundLocation(roundLocation);
        } else {
            throw new Exception("No round location");
        }
   }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RoundLocation> getAllRoundLocations() {
        return RoundLocationService.getAllRoundLocations();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteRoundLocation(@PathParam("id") long roundLocationId) {
        RoundLocationService.deleteRoundLocation(roundLocationId);
    }
}
