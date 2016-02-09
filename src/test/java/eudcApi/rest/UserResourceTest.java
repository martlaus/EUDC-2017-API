package eudcApi.rest;

import eudcApi.common.test.ResourceIntegrationTestBase;
import eudcApi.model.User;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mart on 26.10.15.
 */
public class UserResourceTest extends ResourceIntegrationTestBase {

    @Test
    public void addUser() {
        User user = new User();
        user.setEmail("test@test.ee");
        user.setPassword("testpw");

        Response response = doPost("user", Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

    }

    @Test
    public void getAll() {
        Response response = doGet("user");

        List<User> users = response.readEntity(new GenericType<List<User>>() {
        });

        assertNotNull(users.get(0));
        assertNotNull(users.get(1));
    }
}
