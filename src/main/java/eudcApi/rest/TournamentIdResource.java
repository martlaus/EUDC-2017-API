package eudcApi.rest;

import eudcApi.model.TournamentId;
import eudcApi.service.TournamentIdService;
import eudcApi.utils.AuthUtils;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * Created by Heleriin on 09/08/2017.
 */
@Path("tournamentId")
public class TournamentIdResource {

    @Inject
    private TournamentIdService tournamentIdService;

    private SecurityContext securityContext;

    private static final AuthUtils authentication = new AuthUtils();

    @Context
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @POST
    @RolesAllowed("ADMIN")
    public TournamentId createTournamentId(TournamentId tournamentId) {
        if (authentication.isUserRoleAdmin(securityContext)) {
            return tournamentIdService.createTournamentId(tournamentId);
        }
        return null;
    }

    @DELETE
    @RolesAllowed("ADMIN")
    public void deleteTournamentId(TournamentId tournamentId) {
        if (authentication.isUserRoleAdmin(securityContext)) {
            tournamentIdService.deleteTournamentId(tournamentId);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TournamentId> getAllTournamentIds() {
        return tournamentIdService.getAllTournamentIds();
    }
}