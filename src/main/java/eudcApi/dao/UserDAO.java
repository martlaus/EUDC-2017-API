package eudcApi.dao;

import eudcApi.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
public class UserDAO {

    @Inject
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    public User saveUser(User user) {

        User merged;
        try {
            merged = entityManager.merge(user);
            entityManager.persist(merged);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException("Exception when persisting user.");
        }

        return merged;
    }

    public User getUserByEmail(String email) {
        TypedQuery<User> findByCode = entityManager
                .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);

        User user = null;
        try {
            user = findByCode.setParameter("email", email).getSingleResult();
        } catch (NoResultException ex) {
            // ignore
        }

        return user;
    }

    public void delete(User user) {
        entityManager.remove(user);
    }
}
