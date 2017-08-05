package eudcApi.service.tabbie

import eudcApi.dao.UserDAO
import eudcApi.dao.tabbie.RoundDAO
import eudcApi.dao.tabbie.TabbieRepository
import eudcApi.model.TimerCard
import eudcApi.model.User
import eudcApi.model.tabbie.Round
import eudcApi.service.TimerCardService
import groovy.json.JsonSlurper
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import javax.inject.Inject

/**
 * Created by mart on 18.07.17.
 */
class TabbieDataServices {
    public static final slurper = new JsonSlurper()
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

    @Inject
    private TabbieRepository tabbieRepository

    @Inject
    private RoundDAO roundDAO

    @Inject
    private UserDAO userDAO

    @Inject
    private TimerCardService timerCardService

    public void getRounds() {
        String tournamentJson = tabbieRepository.getRoundsByTournamentId(null)//todo: get id from admin panel
        def result = slurper.parseText(tournamentJson)

        result.each { round ->
            Round build = Round.newBuilder()
                    .id(round.id)
                    .label(round.label)
                    .motion(round.motion)
                    .infoslide(round.infoslide)
                    .prepStarted(formatter.parseDateTime(round.prep_started))
                    .roundLink(round._links.self.api.href)
                    .build()

            if (!roundDAO.getRoundById(build.getId())) {
                roundDAO.saveRound(build)
                createTimerCards(build)
            }
        }
    }

    private void createTimerCards(Round round) {
        String debatesString = tabbieRepository.getDebatesByRoundId(round.id.toString())
        def debates = slurper.parseText(debatesString)

        for (debate in debates) {
            for (debater in debate.participants.debaters) {
                User user = userDAO.getUserByTabbieId(debater.userId)

                if (user) {
                    def now = DateTime.now()
                    TimerCard timerCard = TimerCard.newBuilder()
                            .endDate(round.getPrepStarted().plusMinutes(15))
                            .users([user])
                            .title("Your round is starting in " + debate.venue)
                            .locationId("")//todo: map venues to location id-s
                            .fullLocation(debate.venue)
                            .topic(round.getMotion())
                            .team(debater.position)
                            .build()

                    timerCardService.saveTimerCard(timerCard)
                }
            }
        }
    }

    String checkTabbieUsersRole(User user) {
        def tabbieUser = tabbieRepository.getTabbieRole(user)

        def slurper = new JsonSlurper()
        def result = slurper.parseText(tabbieUser)

        return result.role;
    }
}
