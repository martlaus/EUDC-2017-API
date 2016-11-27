package eudcApi.dao;

import eudcApi.model.Feedback;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by mart on 22.11.16.
 */
public class FeedbackDAO {

    @Inject
    private EntityManager entityManager;

    public List<Feedback> findAll() {
        return entityManager
                .createQuery("SELECT f FROM Feedback f ORDER BY f.created DESC", Feedback.class)
                .getResultList();
    }

    public Feedback saveFeedback(Feedback feedback) {
        Feedback savedEvent;
        try {
            savedEvent = entityManager.merge(feedback);
            entityManager.persist(savedEvent);
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Exception when persisting feedback.");
        }

        return savedEvent;
    }
}
