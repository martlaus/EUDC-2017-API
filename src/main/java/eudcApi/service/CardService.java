package eudcApi.service;

import eudcApi.dao.CardDAO;
import eudcApi.dao.UserDAO;
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
    private CardDAO cardDAO;

    @Inject
    private UserService userService;

    public Card saveCard(Card card) {
        card.setUsers(userService.getAllUsers());

        card.setCreated(DateTime.now());
        return cardDAO.saveCard(card);
    }

    public List<Card> getAllCards() {
        return cardDAO.findAll();
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