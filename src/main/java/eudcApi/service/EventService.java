package eudcApi.service;

import eudcApi.dao.EventDAO;
import eudcApi.model.Event;
import eudcApi.model.EventV2;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 11.10.16.
 */
public class EventService {

    @Inject
    private EventDAO eventDAO;

    public List<Event> getAllEvents() {
        return eventDAO.findAll();
    }

    public List<EventV2> getAllEventsv2() {
        List<EventV2> result = new ArrayList<>();
        List<Event> all = eventDAO.findAll();
        all.forEach(event -> result.add(EventV2.newBuilder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .location(event.getLocation())
                .eventType(event.getEventType())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build()));
        return result;
    }

    public Event createEvent(Event event) {
        //Event has to have a title
        if (StringUtils.isEmpty(event.getTitle())) return null;
        return eventDAO.saveEvent(event);
    }

    public void deleteEvent(Event event) {
        eventDAO.deleteEvent(event);
    }
}
