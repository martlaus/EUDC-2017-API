package eudcApi.service.tabbie

import eudcApi.dao.tabbie.TabbieRepository
import eudcApi.model.User
import groovy.json.JsonSlurper

import javax.inject.Inject

class TabbieLoginService {

    @Inject
    private TabbieDataServices tabbieDataServices

    @Inject
    private TabbieRepository tabbieRepository

    User getTabbieUser(User loginData) {
        def slurper = new JsonSlurper()
        def result = slurper.parseText(tabbieRepository.doTabbieLogin(loginData))

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
