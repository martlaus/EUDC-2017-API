package eudcApi.rest;

import eudcApi.common.test.ResourceIntegrationTestBase;
import eudcApi.model.AuthenticatedUser;
import eudcApi.model.Card;
import eudcApi.model.Event;
import eudcApi.model.User;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 11.10.16.
 */
public class EventResourceTest extends ResourceIntegrationTestBase {

    @Ignore
    @Test
    public void getAll() {
        Response response = doGet("event");

        List<Event> events = response.readEntity(new GenericType<List<Event>>() {
        });

        assertNotNull(events.get(0));
        assertNotNull(events.get(1));
    }

    @Test
    public void create() {
        User user = new User();
        user.setEmail("mart@mart.kz");
        user.setPassword("mart");

        Response response = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });

        assertNotNull(authenticatedUser.getToken());
        String token = authenticatedUser.getToken();

        Event event = new Event();
        event.setTitle("It was not your fault but mine");
        event.setDescription("Ditmas");

        Response response2 = getTarget("event", new LogoutResourceTest.LoggedInUserFilter(token)).request().accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(event, MediaType.APPLICATION_JSON_TYPE));

        Event returnedEvent = response2.readEntity(Event.class);

        assertNotNull(returnedEvent);
        assertEquals(event.getTitle(), returnedEvent.getTitle());

    }
}
