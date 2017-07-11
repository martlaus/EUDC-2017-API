package eudcApi.dao;

import eudcApi.model.EventType;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Heleriin on 11/07/2017.
 */
public class EventTypeDAO {

    @Inject
    private EntityManager entityManager;


    public List<EventType> findAll() {
        return entityManager
                .createQuery("SELECT c FROM EventType", EventType.class)
                .getResultList();
    }

}
