package eudcApi.dao;

import eudcApi.model.RoundLocation;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * Created by karl on 17.04.17.
 */
public class RoundLocationDAO {

	@Inject
    private EntityManager entityManager;

    public List<RoundLocation> findAll() {
        return entityManager.createQuery("from RoundLocation", RoundLocation.class).getResultList();
    }

    public RoundLocation saveRoundLocation(RoundLocation roundLocation) {

        RoundLocation merged;
        try {
            merged = entityManager.merge(roundLocation);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting round location.");
        }

        return merged;
    }

    public void delete(RoundLocation roundLocation) {
        entityManager.remove(roundLocation);
    }

    public void deleteRoundLocation(long roundLocationId) {
        entityManager
                .createNativeQuery("DELETE FROM RoundLocation WHERE id = :roundLocationId")
                .setParameter("roundLocationId", roundLocationId).executeUpdate();
    }
}
