package eudcApi.dao.tabbie;

import eudcApi.model.tabbie.Round;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mart on 25.07.17.
 */
public class RoundDAO {

    @Inject
    private EntityManager entityManager;

    public List<Round> findAll() {
        return entityManager.createQuery("FROM Round", Round.class).getResultList();
    }


    public Round saveRound(Round round) {
        Round merged;
        try {
            merged = entityManager.merge(round);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting round.");
        }

        return merged;
    }

    public Round getRoundById(Long id) {
        TypedQuery<Round> findByCode = entityManager
                .createQuery("SELECT u FROM Round u WHERE u.id = :id", Round.class);

        Round round = null;
        try {
            round = findByCode.setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            // ignore
        }

        return round;
    }
}
