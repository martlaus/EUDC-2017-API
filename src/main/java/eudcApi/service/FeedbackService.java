package eudcApi.service;

import eudcApi.dao.FeedbackDAO;
import eudcApi.model.Feedback;
import eudcApi.model.User;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mart on 22.11.16.
 */
public class FeedbackService {

    @Inject
    private FeedbackDAO feedbackDAO;

    public Feedback createFeedback(Feedback feedback, User user) {
        feedback.setUser(user);
        feedback.setCreated(DateTime.now());

        return feedbackDAO.saveFeedback(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return  feedbackDAO.findAll();
    }
}
