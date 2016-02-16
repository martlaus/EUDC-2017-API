package eudcApi.dao;

import eudcApi.common.test.DatabaseTestBase;
import eudcApi.model.Card;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

//Created by karl on 16.02.16
public class CardDAOTest extends DatabaseTestBase {
	
	@Inject
    private CardDAO cardDAO;

    @Test
    public void findAll() {
        List<Card> Cards = cardDAO.findAll();

        assertValidCard(Cards.get(0));
        assertValidCard(Cards.get(1));
    }

    @Test
    public void saveCard() {
        Card Card = new Card();
        Card.setTitle("Lorem Ipsum");
        Card.setDescription("Sed omnium volumus voluptua te.");

        int initialSize = cardDAO.findAll().size();

        cardDAO.saveCard(Card);

        assertEquals(initialSize + 1, cardDAO.findAll().size());
    }

    private void assertValidCard(Card Card) {
        assertNotNull(Card.getId());
        if (Card.getId() == 1) {
            assertEquals("Lorem Ipsum", Card.getTitle());
        } else if (Card.getId() == 2) {
            assertEquals("Ipsum Lorem", Card.getTitle());
        } else {
            fail("Card with unexpected id.");
        }
    }
}
