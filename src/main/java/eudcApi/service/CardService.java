package eudcApi.service;

import eudcApi.dao.CardDAO;
import eudcApi.model.Card;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by karl on 15.02.16.
 */
public class CardService {

    @Inject
    private CardDAO cardDAO;

    public Card saveCard(Card card) {

        card.setCreated(DateTime.now());
        return cardDAO.saveCard(card);
    }

    public List<Card> getAllCards() {
        return cardDAO.findAll();
    }
}
