package eudcApi.rest;

import eudcApi.model.Card;
import eudcApi.service.CardService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by karl on 15.02.16.
 */
@Path("card")
public class CardResource {

    @Inject
    private CardService cardService;

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
        return cardService.getAllCards();
    }
}
