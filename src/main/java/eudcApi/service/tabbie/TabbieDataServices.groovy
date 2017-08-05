package eudcApi.service.tabbie


import eudcApi.dao.tabbieRepository.RoundDAO
import eudcApi.dao.tabbieRepository.TabbieRepository
import eudcApi.model.tabbie.Round
import groovy.json.JsonSlurper
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import javax.inject.Inject

/**
 * Created by mart on 18.07.17.
 */
class TabbieDataServices {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

    @Inject
    private TabbieRepository tabbieRepository

    @Inject
    private RoundDAO roundDAO;

    public void getRounds() {
        String tournamentJson = tabbieRepository.getRoundsByTournamentId(null);
        def slurper = new JsonSlurper()
        def result = slurper.parseText(tournamentJson)

        for (round in result) {
            Round build = Round.newBuilder()
                    .id(round.id)
                    .label(round.label)
                    .motion(round.motion)
                    .infoslide(round.infoslide)
                    .prepStarted(formatter.parseDateTime(round.prep_started))
                    .roundLink(round._links.self.api.href)
                    .build()

            roundDAO.saveRound(build)
        }
    }
}
