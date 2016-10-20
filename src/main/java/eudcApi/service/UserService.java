package eudcApi.service;

import eudcApi.dao.AuthenticatedUserDAO;
import eudcApi.dao.UserDAO;
import eudcApi.model.AuthenticatedUser;
import eudcApi.model.User;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by mart on 25.10.15.
 */
public class UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private AuthenticatedUserDAO authenticatedUserDAO;

    private SecureRandom random = new SecureRandom();

    private String existingPw;

    public User saveUser(User user) {
        //check if data is according to business rules
        user.setRole("USER");
        //secure pw
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashed);
        } else {
            existingPw = getUserByEmail(user.getEmail()).getPassword();
            user.setPassword(existingPw);
        }

        user.setCreated(DateTime.now());
        return userDAO.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public AuthenticatedUser logIn(User user) throws Exception {
        User returnedUser = getUserByEmail(user.getEmail());
        AuthenticatedUser returnedAuthenticatedUser = null;

        if (returnedUser != null && BCrypt.checkpw(user.getPassword(), returnedUser.getPassword())) {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser();
            authenticatedUser.setUser(returnedUser);
            authenticatedUser.setToken(new BigInteger(130, random).toString(32));

            try {
                returnedAuthenticatedUser = authenticatedUserDAO.createAuthenticatedUser(authenticatedUser);
            } catch (Exception e) {
                authenticatedUser.setToken(new BigInteger(130, random).toString(32));
                returnedAuthenticatedUser = authenticatedUserDAO.createAuthenticatedUser(authenticatedUser);
            }

        }

        return returnedAuthenticatedUser;
    }
}
