package eudcApi.dao;

import eudcApi.model.Card;

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
            throw new RuntimeException("Exception when persisting user.");
        }

        return merged;
    }

    public void delete(Card card) {
        entityManager.remove(card);
    }
}
