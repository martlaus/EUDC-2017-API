package eudcApi.rest;

import eudcApi.common.test.ResourceIntegrationTestBase;
import eudcApi.model.AuthenticatedUser;
import eudcApi.model.Card;
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
import static org.junit.Assert.assertTrue;

/**
 * Created by mart on 26.10.15.
 */
public class UserResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void addUser() {
        User newuser = new User();
        newuser.setEmail("test@test.ee");
        newuser.setPassword("testpw");

        User user = new User();
        user.setEmail("mart@mart.kz");
        user.setPassword("mart");

        Response response = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });

        assertNotNull(authenticatedUser.getToken());
        String token = authenticatedUser.getToken();

        Response response2 = getTarget("user", new LogoutResourceTest.LoggedInUserFilter(token)).request().accept
                (MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(newuser, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response2.getStatus());
    }

    @Test
    public void createUserWithCards() {
        User user = new User();
        user.setEmail("kkksp@mart.kz");
        user.setPassword("mart");

        User loginUser = new User();
        loginUser.setEmail("mart@mart.kz");
        loginUser.setPassword("mart");

        Response response = doPost("signin", Entity.entity(loginUser, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser = response.readEntity(new GenericType<AuthenticatedUser>() {
        });

        Response response2 = getTarget("user", new LogoutResourceTest.LoggedInUserFilter(authenticatedUser.getToken()))
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

        User loggedInUser = response2.readEntity(new GenericType<User>() {
        });

        Response response3 = doPost("signin", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        AuthenticatedUser authenticatedUser2 = response3.readEntity(new GenericType<AuthenticatedUser>() {
        });

        Response response4 = getTarget("card", new LogoutResourceTest.LoggedInUserFilter(authenticatedUser2
                .getToken()))
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();

        List<Card> cards = response4.readEntity(new GenericType<List<Card>>() {
        });
        assertTrue(cards.size() >= 3);
    }

    @Ignore
    @Test
    public void getAll() {
        Response response = doGet("user");

        List<User> users = response.readEntity(new GenericType<List<User>>() {
        });

        assertNotNull(users.get(0));
        assertNotNull(users.get(1));
    }
}
