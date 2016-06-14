package eudcApi.dao;

import eudcApi.model.Location;
import eudcApi.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import java.util.List;

/**
 * Created by rico on 12.06.16.
 */
public class LocationDAO {

	@Inject
    private EntityManager entityManager;

    public List<Location> findAll() {
        return entityManager.createQuery("from Location", Location.class).getResultList();
    }

    public Location saveLocation(Location location) {

        Location merged;
        try {
            merged = entityManager.merge(location);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting location.");
        }

        return merged;
    }

    public void delete(Location location) {
        entityManager.remove(location);
    }

    public void deleteLocation(long locationId) {
        entityManager
                .createNativeQuery("DELETE FROM Location WHERE id = :locationId")
                .setParameter("locationId", locationId).executeUpdate();
    }
}
