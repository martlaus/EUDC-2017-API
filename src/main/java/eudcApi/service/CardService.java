package eudcApi.service;

import eudcApi.dao.CardDAO;
import eudcApi.model.Card;
import eudcApi.model.User;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by karl on 15.02.16.
 */
public class CardService {

    @Inject
    private UserService userService;

    @Inject
    private CardDAO cardDAO;

    public Card saveCard(Card card) {
        card.setUsers(userService.getAllUsers());

        card.setCreated(DateTime.now());
        return cardDAO.saveCard(card);
    }

    public List<Card> getAllCards() {
        return cardDAO.findAll();
    }

    public void generateCards() {
        userService.getAllUsers().forEach(u -> cardDAO.createUserCards(u));
    }

    public List<Card> getUsersCards(User user) {
        return cardDAO.findUsersCards(user);
    }

    public void deleteUserCard(User user, long cardId) {
        cardDAO.deleteUserCard(user, cardId);
    }
    public void deleteCardAsAdmin(long cardId) {
        cardDAO.deleteUserCardAsAdmin(cardId);
    }
}