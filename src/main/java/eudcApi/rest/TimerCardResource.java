package eudcApi.rest;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.TimerCard;
import eudcApi.rest.filter.EudcApiPrincipal;
import eudcApi.service.TimerCardService;

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

/**
 * Created by karl on 13.03.16.
 */
@Path("timercard")
public class TimerCardResource {

    @Inject
    private TimerCardService timerCardService;
    
    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

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
    	EudcApiPrincipal EudcApiPrincipal = (EudcApiPrincipal) securityContext.getUserPrincipal();
        AuthenticatedUser authenticatedUser = null;
        if (EudcApiPrincipal != null) {
            authenticatedUser = EudcApiPrincipal.getAuthenticatedUser();
        }
        
        if (EudcApiPrincipal != null && authenticatedUser != null) {
            return timerCardService.getUsersTimerCards(authenticatedUser.getUser());
        } else {
        	return timerCardService.getAllTimerCards();
        }
    }
    
    @DELETE
    @Path("{timercardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUsersCard(@PathParam("timercardId") long timercardId) {
        EudcApiPrincipal EudcApiPrincipal = (EudcApiPrincipal) securityContext.getUserPrincipal();
        AuthenticatedUser authenticatedUser = null;
        
        if (EudcApiPrincipal != null) {
            authenticatedUser = EudcApiPrincipal.getAuthenticatedUser();
        }

        if (EudcApiPrincipal != null && authenticatedUser != null) {
            timerCardService.deleteUsersTimerCard(authenticatedUser.getUser(), timercardId);
        }

    }
}
