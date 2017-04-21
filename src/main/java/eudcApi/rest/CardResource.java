package eudcApi.rest;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.Card;
import eudcApi.model.User;
import eudcApi.rest.filter.EudcApiPrincipal;
import eudcApi.service.CardService;
import eudcApi.service.OneSignalService;
import eudcApi.utils.AuthUtils;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

/**
 * Created by karl on 15.02.16.
 */
@Path("card")
public class CardResource {

    @Inject
    private CardService cardService;
    
    @Inject
    private OneSignalService oneSignalService;

    private SecurityContext securityContext;

    private static final AuthUtils authentication = new AuthUtils();

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void addCard(Card card) throws Exception {
        if (card != null) {
            cardService.saveCard(card);
            
            if(card.getSendPushAll() == true){
            oneSignalService.sendAll(card.getTitle());
            }
        } else {
            throw new Exception("No card");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> getAllCards() {
        AuthenticatedUser authenticatedUser = authentication.getAuthUser(securityContext);

        if (authentication.isUserAuthenticated(authenticatedUser)) {
            return cardService.getUsersCards(authenticatedUser.getUser());
        } else {
            return cardService.getAllCards();
        }
    }

    @DELETE
    @Path("{cardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUsersCard(@PathParam("cardId") long cardId) {
        AuthenticatedUser authenticatedUser = authentication.getAuthUser(securityContext);
        if (authentication.isUserRoleUser(securityContext)) {
            if (authenticatedUser != null) {
                cardService.deleteUserCard(authenticatedUser.getUser(), cardId);
            }
        } else if (authentication.isUserRoleAdmin(securityContext)) {
            cardService.deleteCardAsAdmin(cardId);
        }
    }


}

