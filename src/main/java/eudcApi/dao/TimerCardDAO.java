package eudcApi.dao;

import eudcApi.model.TimerCard;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import java.util.List;

/**
 * Created by karl on 13.03.16.
 */
public class TimerCardDAO {

	@Inject
    private EntityManager entityManager;

    public List<TimerCard> findAll() {
        return entityManager.createQuery("from TimerCard", TimerCard.class).getResultList();
    }

    public TimerCard saveTimerCard(TimerCard timercard) {

        TimerCard merged;
        try {
            merged = entityManager.merge(timercard);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting timer card.");
        }

        return merged;
    }

    public void delete(TimerCard timercard) {
        entityManager.remove(timercard);
    }
}
