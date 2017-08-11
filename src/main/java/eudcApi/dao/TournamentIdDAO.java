package eudcApi.dao;

import eudcApi.model.TournamentId;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TournamentIdDAO {

    @Inject
    private EntityManager entityManager;

    public List<TournamentId> findAll() {
        return entityManager
                .createQuery("FROM TournamentId", TournamentId.class)
                .getResultList();
    }

    public TournamentId saveTournamentId(TournamentId tournamentId) {
        List<TournamentId> all = findAll();

        if (all.size() > 0) {
            try {
                entityManager
                        .createNativeQuery("UPDATE TournamentId SET tournamentId = :tournamentId WHERE id = 1;")
                        .setParameter("tournamentId", tournamentId.getTournamentId()).executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (all.size() == 0){
            try {
                entityManager
                        .createNativeQuery("INSERT INTO TournamentId (id, tournamentId) VALUES (1, :tournamentId);")
                        .setParameter("tournamentId", tournamentId.getTournamentId()).executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tournamentId;
    }

    public void deleteTournamentId(TournamentId tournamentId) {
        entityManager
                .createNativeQuery("DELETE FROM TournamentId WHERE id = :id")
                .setParameter("id", tournamentId.getId()).executeUpdate();
    }
}