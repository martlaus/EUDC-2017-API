package eudcApi.service.tabbie

import eudcApi.dao.tabbie.TabbieRepository
import eudcApi.model.User
import eudcApi.rest.LoginResource
import groovy.json.JsonSlurper
import org.apache.log4j.Logger

import javax.inject.Inject

class TabbieLoginService {
    private static final Logger logger = Logger.getLogger(TabbieLoginService.class);

    @Inject
    private TabbieDataServices tabbieDataServices

    @Inject
    private TabbieRepository tabbieRepository

    User getTabbieUser(User loginData) {
        def slurper = new JsonSlurper()
        logger.info("Doing tabbie login");
        def login = tabbieRepository.doTabbieLogin(loginData)
        def result = slurper.parseText(login)
        logger.info("Received data from tabbie: "  + login)

        if (result && result.id) {
            User user = new User()

            user.setEmail(result.email)
            user.setTabbieId(result.id)
            user.setTournamentRole(tabbieDataServices.checkTabbieUsersRole(user))
//        user.setTabbieToken((String) jsonMap.get("token"));
            return user
        }

        return null
    }
}
