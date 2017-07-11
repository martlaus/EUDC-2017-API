package eudcApi.service;

import eudcApi.dao.EventTypeDAO;
import eudcApi.model.EventType;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Heleriin on 11/07/2017.
 */
public class EventTypeService {

    @Inject
    private EventTypeDAO eventTypeDAO;

    public List<EventType> getAllEventTypes() {
        return eventTypeDAO.findAll();
    }


}
