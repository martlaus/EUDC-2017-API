package eudcApi.dao;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.Card;
import eudcApi.model.User;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by karl on 15.02.16.
 */
public class CardDAO {

    @Inject
    private EntityManager entityManager;
    @Inject
    private UserDAO userDAO;

    public List<Card> findAll() {
        return entityManager.createQuery(
                "SELECT c FROM Card c ORDER BY c.pinned DESC, c.created DESC",
                Card.class).getResultList();
    }

    public Card saveCard(Card card) {

        Card merged;
        try {
            merged = entityManager.merge(card);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting card.");
        }

        return merged;
    }
    public Card testMakeCard(String title, String description) {
        Card card = new Card();
        card.setTitle(title);
        card.setDescription(description);
        card.setUsers(userDAO.findAll());
        card.setCreated(DateTime.now());

        saveCard(card);

        card = findAll().get(findAll().size() - 1);

        return card;
    }

    public void delete(Card card) {
        entityManager.remove(card);
    }

    public List<Card> findUsersCards(User user) {
        TypedQuery<Card> findByUser = entityManager
                .createQuery("SELECT c FROM Card c LEFT JOIN FETCH c.users as u WHERE u = :user",
                        Card.class);

        List<Card> cards = null;

        try {
            cards = findByUser.setParameter("user", user).getResultList();
        } catch (Exception e) {
            //ignore   -No, you fucking don't ignore.
            e.printStackTrace();

        }

        return cards;
    }
    public List<Card> findCardsByCardId(long cardId) {
        TypedQuery<Card> findById = entityManager
                .createQuery("SELECT c FROM Card c LEFT JOIN c.users as u WHERE c.id = :card",
                        Card.class);

        List<Card> cards = null;

        try {
            cards = findById.setParameter("card", cardId).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }

    public void deleteUserCard(User user, long cardId) {
        entityManager
                .createNativeQuery("DELETE FROM Card_User WHERE user = :user AND card = :cardId")
                .setParameter("user", user.getId())
                .setParameter("cardId", cardId).executeUpdate();
    }

    private void deleteCard(long cardId) {
        entityManager
                .createNativeQuery("DELETE FROM Card WHERE id = :cardId")
                .setParameter("cardId", cardId).executeUpdate();
    }

    private void deleteCardRelation(long cardId) {
        entityManager
                .createNativeQuery("DELETE FROM Card_User WHERE card = :cardId")
                .setParameter("cardId", cardId).executeUpdate();
    }

    public void deleteUserCardAsAdmin(long cardId) {
        deleteCardRelation(cardId);
        deleteCard(cardId);
    }

}