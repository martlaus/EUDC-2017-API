package eudcApi.service.tabbie

import eudcApi.dao.tabbieRepository.TabbieRepository
import eudcApi.model.User

import groovy.json.JsonSlurper

import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

public class TabbieRoleService {

    String UTF8 = StandardCharsets.UTF_8.name();

    @Inject
    private TabbieRepository tabbieRepository;

	public String checkTabbieUsersRole(User user) {

        def tabbieUser = tabbieRepository.getTabbieRole(user)

            def slurper = new JsonSlurper()
            def result = slurper.parseText(tabbieUser)

            return result.role;
    }

}
