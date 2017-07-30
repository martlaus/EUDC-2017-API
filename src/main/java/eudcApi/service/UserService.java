package eudcApi.service;

import eudcApi.dao.AuthenticatedUserDAO;
import eudcApi.dao.UserDAO;
import eudcApi.model.AuthenticatedUser;
import eudcApi.model.User;
import eudcApi.rest.TimerCardResource;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import static org.apache.log4j.Logger.*;

/**
 * Created by mart on 25.10.15.
 */
public class UserService {
    private static final Logger logger = getLogger(TimerCardResource.class);

    @Inject
    private UserDAO userDAO;

    @Inject
    private AuthenticatedUserDAO authenticatedUserDAO;

    private SecureRandom random = new SecureRandom();



    public User addUser(User user) {
        //secure pw
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String hashed = generatePasswordHash(user);
            user.setPassword(hashed);
        }

        return saveUser(user);
    }

    private String generatePasswordHash(User user) {
        return BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    }

    public User updateUser(User newUser, User prevUser) {
        if (newUser.getRole() != null && !newUser.getRole().isEmpty()) {
            prevUser.setRole(newUser.getRole());
        }

        if (newUser.getTournamentRole() != null && !newUser.getTournamentRole().isEmpty()) {
            prevUser.setTournamentRole(newUser.getTournamentRole());
        }

        if (newUser.getEmail() != null && !newUser.getEmail().isEmpty()) {
            prevUser.setEmail(newUser.getEmail());
        }

        if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
            String hashed = generatePasswordHash(prevUser);
            prevUser.setPassword(hashed);
        }

        return saveUser(prevUser);
    }

    public AuthenticatedUser loginWithTabbieUser(User user) throws Exception {
        logger.info("Logging in with user, with email: " + user.getEmail());
        User returnedUser = getUserByEmail(user.getEmail());

        if (returnedUser == null) {
            user.setRole("USER");
            returnedUser = saveUser(user);
            logger.info("New user created, with email: " + user.getEmail());
        }

        return loginWithoutPassword(returnedUser);
    }

    private User saveUser(User user) {
        user.setCreated(DateTime.now());
        return userDAO.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    public AuthenticatedUser logIn(User user) throws Exception {
        User returnedUser = getUserByEmail(user.getEmail());
        AuthenticatedUser returnedAuthenticatedUser = null;

        if (returnedUser != null && BCrypt.checkpw(user.getPassword(), returnedUser.getPassword())) {
            returnedAuthenticatedUser = loginWithoutPassword(returnedUser);
        }

        return returnedAuthenticatedUser;
    }

    private AuthenticatedUser loginWithoutPassword(User returnedUser) throws Exception {
        AuthenticatedUser returnedAuthenticatedUser;
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setUser(returnedUser);
        authenticatedUser.setToken(new BigInteger(130, random).toString(32));

        try {
            returnedAuthenticatedUser = authenticatedUserDAO.createAuthenticatedUser(authenticatedUser);
        } catch (Exception e) {
            authenticatedUser.setToken(new BigInteger(130, random).toString(32));
            returnedAuthenticatedUser = authenticatedUserDAO.createAuthenticatedUser(authenticatedUser);
        }
        return returnedAuthenticatedUser;
    }
}
