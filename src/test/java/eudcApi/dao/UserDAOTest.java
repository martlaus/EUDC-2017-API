package eudcApi.dao;

import eudcApi.common.test.DatabaseTestBase;
import eudcApi.model.User;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mart on 25.10.15.
 */
public class UserDAOTest extends DatabaseTestBase {

    @Inject
    private UserDAO userDAO;

    @Test
    public void findAll() {
        List<User> users = userDAO.findAll();

        assertValidUser(users.get(0));
        assertValidUser(users.get(1));
    }

    @Test
    public void getUserByEmail(){
        User user = userDAO.getUserByEmail("admin@admin.kz");

        assertNotNull(user.getId());
        assertEquals("admin@admin.kz", user.getEmail());
        assertNotNull(user.getPassword());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setEmail("kazaa@lankara.ru");
        user.setPassword("parool");

        int initialSize = userDAO.findAll().size();

        userDAO.saveUser(user);

        assertEquals(initialSize + 1, userDAO.findAll().size());
    }

    private void assertValidUser(User user) {
        assertNotNull(user.getId());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPassword());
        if (user.getId() == 1) {
            assertEquals("admin@admin.kz", user.getEmail());
        } else if (user.getId() == 2) {
            assertEquals("user@user.kz", user.getEmail());
        } else {
            fail("User with unexpected id.");
        }
    }
}
