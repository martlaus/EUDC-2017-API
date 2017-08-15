package eudcApi.service.tabbie

import eudcApi.dao.UserDAO
import eudcApi.dao.tabbie.RoundDAO
import eudcApi.dao.tabbie.TabbieRepository
import eudcApi.model.TimerCard
import eudcApi.model.User
import eudcApi.model.tabbie.Round
import eudcApi.service.OneSignalService
import eudcApi.service.TimerCardService
import eudcApi.service.TournamentIdService
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

    @Inject
    private TournamentIdService tournamentIdService

    @Inject
    private OneSignalService oneSignalService;

    public void generateTimerCardsForNewRounds() {
        String tournamentJson = tabbieRepository.getRoundsByTournamentId(tournamentIdService.getTournamentId())
        def result = slurper.parseText(tournamentJson)
        if (!result) {
            return
        }

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

                println "Sending notifcations for round " + build.label
                //Send a new round notification
                 oneSignalService.sendAll("Round " + build.label + " has been published." +
                       " The motion is " + round.motion)
            }
        }

        println "Finished parsing rounds"
    }

    public String getUserBarcode(String tabbieUserId) {
        String barcodeJson = tabbieRepository.getBarcode(tabbieUserId)
        def result = slurper.parseText(barcodeJson).b64

        return result
    }

    private void createTimerCards(Round round) {
        String debatesString = tabbieRepository.getDebatesByRoundId(round.id.toString())
        def debates = slurper.parseText(debatesString)

        for (debate in debates) {

            if (!debate.participants && debate.participants.debaters) {
                for (debater in debate.participants.debaters) {
                    createCardForUser(debater, round, debate)
                }
            }
            if (!debate.participants && debate.participants.adjudicators) {
                for (debater in debate.participants.adjudicators) {
                    createCardForUser(debater, round, debate)
                }
            }
        }
    }

    private void createCardForUser(debater, Round round, debate) {
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

    String checkTabbieUsersRole(User user) {
        def tabbieUser = tabbieRepository.getTabbieRole(user)

        def slurper = new JsonSlurper()
        def result = slurper.parseText(tabbieUser)

        return result.role;
    }
}
