package eudcApi.rest;

import eudcApi.model.Event;
import eudcApi.model.EventV2;
import eudcApi.service.EventService;
import eudcApi.utils.AuthUtils;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by mart on 11.10.16.
 */
@Path("event")
public class EventResource {

    @Inject
    private EventService eventService;

    private SecurityContext securityContext;

    private static final AuthUtils authentication = new AuthUtils();

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @POST
    @RolesAllowed("ADMIN")
    public Event createEvent(Event event) {
        if (authentication.isUserRoleAdmin(securityContext)) {
            return eventService.createEvent(event);
        }
        return null;
    }

    @DELETE
    @RolesAllowed("ADMIN")
    public void deleteEvent(Event event) {
        if (authentication.isUserRoleAdmin(securityContext)) {
            eventService.deleteEvent(event);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GET
    @Path("/v2")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventV2> getAllEventsv2() {
        List<EventV2> allEventsV2 = eventService.getAllEventsv2();
        return allEventsV2;
    }
}
