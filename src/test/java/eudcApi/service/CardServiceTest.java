package eudcApi.service;

import eudcApi.dao.CardDAO;
import eudcApi.model.Card;
import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

// Created by karl on 16.02.16
@RunWith(EasyMockRunner.class)
public class CardServiceTest {

	@TestSubject
    private CardService cardService = new CardService();

    @Mock
    private CardDAO cardDAO;

    @Mock
    private UserService userService;

    @Test
    public void saveCard() {
        Card card = new Card();
        card.setTitle("Lorem Ipsum");
        Capture<Card> capturedCard = newCapture();
        expectCreateCard(capturedCard);
        expect(userService.getAllUsers()).andReturn(new ArrayList<>());

        replay(cardDAO, userService);

        Card returnedCard = cardService.saveCard(card);

        verify(cardDAO, userService);

        assertNotNull(returnedCard);
        assertNotEquals("title", returnedCard.getTitle());

    }

    private void expectCreateCard(Capture<Card> capturedCard) {
        expect(cardDAO.saveCard(EasyMock.capture(capturedCard)))
                .andAnswer(capturedCard::getValue);
    }
	
}
