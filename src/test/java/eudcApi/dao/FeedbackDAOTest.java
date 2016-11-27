package eudcApi.dao;

import eudcApi.common.test.DatabaseTestBase;
import eudcApi.model.Feedback;
import eudcApi.model.User;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by mart on 22.11.16.
 */
public class FeedbackDAOTest extends DatabaseTestBase {

    @Inject
    private FeedbackDAO feedbackDAO;

    @Inject
    private UserDAO userDAO;

    @Test
    public void getAll() {
        List<User> users = userDAO.findAll();

        List<Feedback> initalList = feedbackDAO.findAll();

        Feedback feedback = new Feedback();
        feedback.setContent("asdasd");
        feedback.setUser(users.get(0));
        feedbackDAO.saveFeedback(feedback);
        List<Feedback> feedbackList = feedbackDAO.findAll();

        assertTrue(feedbackList.size() > initalList.size());
    }


}
