package eudcApi.rest;

import eudcApi.model.User;
import eudcApi.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
@Path("user")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void addUser(User user) throws Exception {
        if (user != null) {
            userService.saveUser(user);
        } else {
            throw new Exception("No user");
        }
   }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
