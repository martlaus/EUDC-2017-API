package eudcApi.service;

import eudcApi.dao.EventDAO;
import eudcApi.model.Event;
import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;
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

    public Event createEvent(Event event) {
        //Event has to have a title
        if(StringUtils.isEmpty(event.getTitle())) return null;
        return eventDAO.saveEvent(event);
    }
}
