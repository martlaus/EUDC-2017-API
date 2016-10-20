package eudcApi.dao;

import com.google.inject.Inject;
import eudcApi.common.test.DatabaseTestBase;
import eudcApi.model.Card;
import eudcApi.model.Event;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 11.10.16.
 */
public class EventDAOTest extends DatabaseTestBase {

    @Inject
    private EventDAO eventDAO;

    @Test
    public void findAll() {
        List<Event> events = eventDAO.findAll();

        assertNotNull(events.get(0));
        assertNotNull(events.get(1));
        assertNotNull(events.get(2));
    }

    @Test
    public void save() {
        Event event = new Event();
        event.setTitle("asd");
        eventDAO.saveEvent(event);
        List<Event> events = eventDAO.findAll();

        assertNotNull(events.get(3));
        assertEquals("asd", events.get(3).getTitle());
    }
}
