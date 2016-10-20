package eudcApi.rest;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.Card;
import eudcApi.model.User;
import eudcApi.rest.filter.EudcApiPrincipal;
import eudcApi.service.CardService;

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

    private SecurityContext securityContext;

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void addCard(Card card) throws Exception {
        if (card != null) {
            cardService.saveCard(card);
        } else {
            throw new Exception("No card");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> getAllCards() {
        EudcApiPrincipal EudcApiPrincipal = (EudcApiPrincipal) securityContext.getUserPrincipal();
        AuthenticatedUser authenticatedUser = null;
        try {
            if (EudcApiPrincipal != null) {
                authenticatedUser = EudcApiPrincipal.getAuthenticatedUser();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (EudcApiPrincipal != null && authenticatedUser != null) {
            return cardService.getUsersCards(authenticatedUser.getUser());
        } else {
            return cardService.getAllCards();
        }
    }

    @DELETE
    @Path("{cardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUsersCard(@PathParam("cardId") long cardId) {
        AuthenticatedUser authenticatedUser = getAuthUser();
        if (isUserRoleUser()) {
            if (authenticatedUser != null) {
                cardService.deleteUserCard(authenticatedUser.getUser(), cardId);
            }
        } else if (isUserRoleAdmin()) {
            cardService.deleteCardAsAdmin(cardId);
        }
    }

    private AuthenticatedUser getAuthUser() {
        EudcApiPrincipal EudcApiPrincipal = (EudcApiPrincipal) securityContext.getUserPrincipal();
        if (EudcApiPrincipal != null) {
            return EudcApiPrincipal.getAuthenticatedUser();
        }
        return null;
    }

    private boolean isUserRoleUser() {
        AuthenticatedUser authenticatedUser = getAuthUser();
        return isUserAuthenticated(authenticatedUser) && authenticatedUser.getUser().getRole().equals("USER");
    }

    private boolean isUserRoleAdmin() {
        AuthenticatedUser authenticatedUser = getAuthUser();
        return isUserAuthenticated(authenticatedUser) && authenticatedUser.getUser().getRole().equals("ADMIN");
    }

    private boolean isUserAuthenticated(AuthenticatedUser authenticatedUser) {
        return authenticatedUser != null;
    }

}

