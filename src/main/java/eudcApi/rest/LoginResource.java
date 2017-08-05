package eudcApi.rest;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.User;
import eudcApi.service.tabbie.TabbieLoginService;
import eudcApi.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * Created by mart on 1.11.15.
 */
@Path("signin")
public class LoginResource {

    @Inject
    private UserService userService;

    @Inject
    private TabbieLoginService tabbieLoginService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticatedUser login(User user) throws Exception {
        User tabbieUser = null;
        if (user.getEmail() != null && user.getPassword() != null) {
            tabbieUser = tabbieLoginService.getTabbieUser(user);
        }

        if (tabbieUser != null) {
            return userService.loginWithTabbieUser(tabbieUser);
        } else {
            AuthenticatedUser authenticatedUser = userService.logIn(user);
            if (authenticatedUser != null) {
                return authenticatedUser;
            }
        }
        throw new RuntimeException("Unable to log in - wrong user info or the user is not registered");
    }
}