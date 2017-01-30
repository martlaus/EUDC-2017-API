package eudcApi.rest;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.TimerCard;
import eudcApi.rest.filter.EudcApiPrincipal;
import eudcApi.service.TimerCardService;
import eudcApi.utils.AuthUtils;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by karl on 13.03.16.
 */
@Path("timercard")
public class TimerCardResource {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TimerCardResource.class);
    @Inject
    private TimerCardService timerCardService;
    
    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    private static AuthUtils authentication = new AuthUtils();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void addTimerCard(TimerCard timercard) throws Exception {
        if (timercard != null) {
            timerCardService.saveTimerCard(timercard);
        } else {
            throw new Exception("No timer card");
        }
   }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TimerCard> getAllTimerCards() {
        AuthenticatedUser authenticatedUser = authentication.getAuthUser(securityContext);

        return authentication.isUserAuthenticated(authenticatedUser) ?
                timerCardService.getUsersTimerCards(authenticatedUser.getUser()) :
                timerCardService.getAllTimerCards();
    }

    // This is deletion, tee-hee
    @DELETE
    @Path("{timercardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUsersCard(@PathParam("timercardId") Long timercardId) {
        AuthenticatedUser authenticatedUser = authentication.getAuthUser(securityContext);

        if (authentication.isUserAuthenticated(authenticatedUser)) {
            timerCardService.deleteUsersTimerCard(authenticatedUser.getUser(), timercardId);
        }
        logger.warn(timercardId.toString());

    }
}
