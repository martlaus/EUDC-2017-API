package eudcApi.dao.tabbieRepository;

import eudcApi.model.tabbie.Round;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
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
}
