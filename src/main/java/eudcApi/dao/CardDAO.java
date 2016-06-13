package eudcApi.dao;

import eudcApi.model.AuthenticatedUser;
import eudcApi.model.Card;
import eudcApi.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by karl on 15.02.16.
 */
public class CardDAO {

    @Inject
    private EntityManager entityManager;

    public List<Card> findAll() {
        return entityManager.createQuery("from Card", Card.class).getResultList();
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
            //ignore
        }

        return cards;
    }

    public void deleteUsersCard(User user, long cardId) {
        entityManager
                .createNativeQuery("DELETE FROM Card_User cu WHERE cu.user = :user AND cu.card = :cardId")
                .setParameter("user", user.getId())
                .setParameter("cardId", cardId).executeUpdate();
    }
}
