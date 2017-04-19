package eudcApi.dao;

import eudcApi.common.test.DatabaseTestBase;
import eudcApi.model.TimerCard;

import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

//Created by karl on 05.04.16
public class TimerCardDAOTest extends DatabaseTestBase {
	
	@Inject
    private TimerCardDAO timerCardDAO;

    @Test
    public void findAll() {
        List<TimerCard> timerCards = timerCardDAO.findAll();

        assertValidCard(timerCards.get(0));
    }

    @Test
    public void saveCard() {
        TimerCard TimerCard = new TimerCard();
        TimerCard.setTitle("Round start notice");
        TimerCard.setFullLocation("U03 223");

        int initialSize = timerCardDAO.findAll().size();

        timerCardDAO.saveTimerCard(TimerCard);

        assertEquals(initialSize + 1, timerCardDAO.findAll().size());
    }

    private void assertValidCard(TimerCard TimerCard) {
        assertNotNull(TimerCard.getId());
        if (TimerCard.getId() == 1) {
            assertEquals("Round start notice", TimerCard.getTitle());
        } else {
            fail("Card with unexpected id.");
        }
    }
}
