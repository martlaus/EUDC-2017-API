package eudcApi.rest;

import eudcApi.db.FlywayDbMigrator;
import eudcApi.model.AuthenticatedUser;
import eudcApi.model.User;
import eudcApi.service.tabbie.TabbieLoginService;
import eudcApi.service.UserService;
import org.apache.log4j.Logger;

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
    private static final Logger logger = Logger.getLogger(LoginResource.class);


    @Inject
    private UserService userService;

    @Inject
    private TabbieLoginService tabbieLoginService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AuthenticatedUser login(User user) throws Exception {
        User tabbieUser = null;
        logger.info("Trying to log in user " +  user.getEmail());
        if (user.getEmail() != null && user.getPassword() != null) {
            logger.info("Trying to get tabbie user for " +  user.getEmail());
            tabbieUser = tabbieLoginService.getTabbieUser(user);

        }

        if (tabbieUser != null) {
            return userService.loginWithTabbieUser(tabbieUser);
        } else {
            logger.info("No tabbie user, trying un and pw " +  user.getEmail());

            AuthenticatedUser authenticatedUser = userService.logIn(user);
            if (authenticatedUser != null) {
                return authenticatedUser;
            }
        }
        throw new RuntimeException("Unable to log in - wrong user info or the user is not registered");
    }
}