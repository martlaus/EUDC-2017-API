package eudcApi.rest;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.Location;
import eudcApi.rest.filter.EudcApiPrincipal;
import eudcApi.service.LocationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

/**
 * Created by rico on 12.06.16.
 */
@Path("location")
public class LocationResource {
	
	@Inject
    private LocationService locationService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void addLocation(Location location) throws Exception {
        if (location != null) {
            locationService.saveLocation(location);
        } else {
            throw new Exception("No location");
        }
   }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteLocation(@PathParam("id") long locationId) {
        locationService.deleteLocation(locationId);
    }
}
