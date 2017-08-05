package eudcApi.dao;

import eudcApi.model.Card;
import eudcApi.model.Event;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by mart on 11.10.16.
 */
public class EventDAO {

    @Inject
    private EntityManager entityManager;

    public List<Event> findAll() {
        return entityManager
                .createQuery("SELECT c FROM Event c ORDER BY TIMESTAMPDIFF(c.startTime, c.endTime) ASC", Event.class)
                .getResultList();
    }

    public Event saveEvent(Event event) {

        Event merged;
        try {
            merged = entityManager.merge(event);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting event.");
        }

        return merged;
    }

    public void deleteEvent(Event event) {
            entityManager
                    .createNativeQuery("DELETE FROM Event WHERE id = :eventId")
                    .setParameter("eventId", event.getId()).executeUpdate();

    }
}
