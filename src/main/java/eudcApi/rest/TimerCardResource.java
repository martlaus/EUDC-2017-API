package eudcApi.rest;

import eudcApi.model.TimerCard;
import eudcApi.service.TimerCardService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

/**
 * Created by karl on 13.03.16.
 */
@Path("timercard")
public class TimerCardResource {

    @Inject
    private TimerCardService timerCardService;

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
        return timerCardService.getAllTimerCards();
    }
}
