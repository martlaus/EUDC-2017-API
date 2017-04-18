package eudcApi.service;

import eudcApi.dao.RoundLocationDAO;
import eudcApi.model.RoundLocation;


import org.joda.time.DateTime;

import javax.inject.Inject;

import java.util.List;

/**
 * Created by karl on 17.04.17.
 */
public class RoundLocationService {

	@Inject
    private RoundLocationDAO roundLocationDAO;

    public RoundLocation saveRoundLocation(RoundLocation roundLocation) {

        roundLocation.setCreated(DateTime.now());
        return roundLocationDAO.saveRoundLocation(roundLocation);
    }

    public List<RoundLocation> getAllRoundLocations() {
        return roundLocationDAO.findAll();
    }

    public void deleteRoundLocation(long roundLocationId) {
        roundLocationDAO.deleteRoundLocation(roundLocationId);
    }
}
