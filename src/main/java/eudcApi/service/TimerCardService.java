package eudcApi.service;

import eudcApi.dao.TimerCardDAO;
import eudcApi.model.Card;
import eudcApi.model.TimerCard;
import eudcApi.model.User;

import org.joda.time.DateTime;

import javax.inject.Inject;

import java.util.List;

/**
 * Created by karl on 13.03.16.
 */
public class TimerCardService {

    @Inject
    private TimerCardDAO timerCardDAO;

    public TimerCard saveTimerCard(TimerCard timercard) {      

        timercard.setCreated(DateTime.now());
        return timerCardDAO.saveTimerCard(timercard); 
        
    }

    public List<TimerCard> getAllTimerCards() {
        return timerCardDAO.findAll();
    }
    
    public List<TimerCard> getUsersTimerCards(User user) {
        return timerCardDAO.findUsersTimerCards(user);
    }
    
    public void deleteUsersTimerCard(User user, long timercardId) {
        timerCardDAO.deleteUsersTimerCard(user, timercardId);
    }
}
