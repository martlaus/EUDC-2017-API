package eudcApi.rest;

import eudcApi.model.User;
import eudcApi.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
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
    @RolesAllowed({"USER", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public void addUser(User user) throws Exception {
        if (user != null) {
            userService.addUser(user);
        } else {
            throw new Exception("No user");
        }
   }

    @POST
    @Path("{userId}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public void editUser(@PathParam("userId") long userId, User user) throws Exception {
        User userById = userService.getUserById(userId);
        if (user != null) {
            userService.updateUser(user, userById);
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
