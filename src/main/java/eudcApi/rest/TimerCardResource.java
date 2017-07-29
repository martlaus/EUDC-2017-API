package eudcApi.rest;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.TimerCard;
import eudcApi.model.User;
import eudcApi.service.TimerCardService;
import eudcApi.utils.AuthUtils;
import org.apache.log4j.Logger;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

/**
 * Created by karl on 13.03.16.
 */
@Path("timercard")
public class TimerCardResource {
	
	//int newTimerCardId;
	
    private static final Logger logger = Logger.getLogger(TimerCardResource.class);
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
            //newTimerCardId  = timerCardService.getAllTimerCards().size();
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
    /*
  	// TODO ADD users card
    @POST
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void addUsersCard(@PathParam("userId") long userId, long newTimerCardId) {
    	timerCardService.addUsersTimerCard(userId, newTimerCardId);	
    }*/
	
    // This is deletion, tee-hee
    @DELETE
    @Path("{timerCardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUsersCard(@PathParam("timerCardId") Long timercardId) {
        AuthenticatedUser authenticatedUser = authentication.getAuthUser(securityContext);

        if (authentication.isUserAuthenticated(authenticatedUser)) {
            timerCardService.deleteUsersTimerCard(authenticatedUser.getUser(), timercardId);
        }
        logger.warn(timercardId.toString());

    }
}
