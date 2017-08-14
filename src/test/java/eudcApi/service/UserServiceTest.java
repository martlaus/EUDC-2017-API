package eudcApi.service;

import eudcApi.dao.CardDAO;
import eudcApi.dao.UserDAO;
import eudcApi.model.User;
import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Created by mart on 1.11.15.
 */
@RunWith(EasyMockRunner.class)
public class UserServiceTest {

    @TestSubject
    private UserService userService = new UserService();

    @Mock
    private CardService cardService;

    @Mock
    private UserDAO userDAO;

    @Mock
    private CardDAO cardDAO;

    @Test
    public void addUser() {
        User user = new User();
        user.setPassword("parool");

        Capture<User> capturedUser = newCapture();
        expectCreateUser(capturedUser);

        replay(userDAO);

        User returnedUser = userService.addUser(user);

        verify(userDAO);

        assertNotNull(returnedUser);
        assertNotEquals("parool", returnedUser.getPassword());

        if (!BCrypt.checkpw("parool", returnedUser.getPassword())) {
            fail("password does not match the hash");
        }
    }

    private void expectCreateUser(Capture<User> capturedUser) {
        expect(userDAO.saveUser(EasyMock.capture(capturedUser)))
                .andAnswer(capturedUser::getValue);
    }
}
