package eudcApi.service;

import eudcApi.dao.TournamentIdDAO;
import eudcApi.model.TournamentId;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Heleriin on 09/08/2017.
 */
public class TournamentIdService {

    @Inject
    private TournamentIdDAO tournamentIdDAO;

    public List<TournamentId> getAllTournamentIds() {
        return tournamentIdDAO.findAll();
    }

    public TournamentId createTournamentId(TournamentId tournamentId) {
        if (tournamentId.getTournamentId() == null) return null;
        return tournamentIdDAO.saveTournamentId(tournamentId);
    }

    public String getTournamentId() {
        List<TournamentId> allTournamentIds = getAllTournamentIds();
        if (allTournamentIds != null && allTournamentIds.size() > 0) {
            return String.valueOf(allTournamentIds.get(allTournamentIds.size() - 1).getTournamentId());
        }

        return null;
    }

    public void deleteTournamentId(TournamentId tournamentId) {
        tournamentIdDAO.deleteTournamentId(tournamentId);
    }
}