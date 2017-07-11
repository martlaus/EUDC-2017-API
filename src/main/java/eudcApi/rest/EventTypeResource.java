package eudcApi.rest;

import eudcApi.model.EventType;
import eudcApi.service.EventTypeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Heleriin on 11/07/2017.
 */

@Path("eventType")
public class EventTypeResource {

    @Inject
    private EventTypeService eventTypeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventType> getAllEventTypes() {
        return eventTypeService.getAllEventTypes();
    }
}
