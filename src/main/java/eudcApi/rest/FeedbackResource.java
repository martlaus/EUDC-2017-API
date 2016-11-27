package eudcApi.rest;

import eudcApi.model.Feedback;
import eudcApi.service.FeedbackService;
import eudcApi.utils.AuthUtils;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by mart on 22.11.16.
 */

@Path("feedback")
public class FeedbackResource {

    @Inject
    private FeedbackService feedbackService;

    private SecurityContext securityContext;

    private static final AuthUtils authentication = new AuthUtils();

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }


    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @POST
    @RolesAllowed({"USER", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Feedback addFeedback(Feedback feedback) {
        return feedbackService.createFeedback(feedback, authentication.getAuthUser(securityContext).getUser());
    }
}
