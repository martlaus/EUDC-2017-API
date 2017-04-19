package eudcApi.dao;

import eudcApi.model.Card;
import eudcApi.model.TimerCard;
import eudcApi.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

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
    
    public List<TimerCard> findUsersTimerCards(User user) {
        TypedQuery<TimerCard> findByUser = entityManager
                .createQuery("SELECT c FROM TimerCard c LEFT JOIN FETCH c.users as u WHERE u = :user",
                        TimerCard.class);

        List<TimerCard> timercards = null;

        try {
            timercards = findByUser.setParameter("user", user).getResultList();
        } catch (Exception e) {
            //ignore
        }

        return timercards;
    }
    /* TODO
    public void addUsersTimerCard(long userId, long timercardId) {
    	entityManager
		.createNativeQuery("INSERT INTO TimerCard_User VALUES(user = :user, timercard = :timercardId)")
        .setParameter("user", userId)
        .setParameter("timercardId", timercardId).executeUpdate();
    } */
    
    public void deleteUsersTimerCard(User user, long timercardId) {
        entityManager
        		.createNativeQuery("DELETE FROM TimerCard_User WHERE user = :user AND timercard = :timercardId")
                .setParameter("user", user.getId())
                .setParameter("timercardId", timercardId).executeUpdate();
    }
}
