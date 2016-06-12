package eudcApi.service;

import eudcApi.dao.LocationDAO;
import eudcApi.model.Location;

import org.joda.time.DateTime;

import javax.inject.Inject;

import java.util.List;

/**
 * Created by rico on 12.06.16.
 */
public class LocationService {

	@Inject
    private LocationDAO locationDAO;

    public Location saveLocation(Location location) {

        location.setCreated(DateTime.now());
        return locationDAO.saveLocation(location);
    }

    public List<Location> getAllLocations() {
        return locationDAO.findAll();
    }
}
