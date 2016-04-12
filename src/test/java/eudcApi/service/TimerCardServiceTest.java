package eudcApi.service;

import eudcApi.dao.TimerCardDAO;
import eudcApi.model.TimerCard;

import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

// Created by karl on 05.04.16
@RunWith(EasyMockRunner.class)
public class TimerCardServiceTest {

	@TestSubject
    private TimerCardService cardService = new TimerCardService();

    @Mock
    private TimerCardDAO timerCardDAO;

    @Test
    public void saveCard() {
        TimerCard timercard = new TimerCard();
        timercard.setTitle("Round start notice");
        Capture<TimerCard> capturedTimerCard = newCapture();
        expectCreateTimerCard(capturedTimerCard);

        replay(timerCardDAO);

        TimerCard returnedCard = cardService.saveTimerCard(timercard);

        verify(timerCardDAO);

        assertNotNull(returnedCard);
        assertNotEquals("title", returnedCard.getTitle());

    }

    private void expectCreateTimerCard(Capture<TimerCard> capturedTimerCard) {
        expect(timerCardDAO.saveTimerCard(EasyMock.capture(capturedTimerCard)))
                .andAnswer(capturedTimerCard::getValue);
    }
	
}
