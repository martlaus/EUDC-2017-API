package eudcApi.rest;

import eudcApi.model.Event;
import eudcApi.service.EventService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by mart on 11.10.16.
 */
@Path("event")
public class EventResource {

    @Inject
    private EventService eventService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        return  eventService.getAllEvents();
    }
}
