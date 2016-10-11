package eudcApi.rest;

import eudcApi.common.test.ResourceIntegrationTestBase;
import eudcApi.model.Card;
import eudcApi.model.Event;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 11.10.16.
 */
public class EventResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void getAll() {
        Response response = doGet("event");

        List<Event> events = response.readEntity(new GenericType<List<Event>>() {
        });

        assertNotNull(events.get(0));
        assertNotNull(events.get(1));
    }
}
