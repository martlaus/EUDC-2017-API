package eudcApi.dao;

import eudcApi.common.test.DatabaseTestBase;
import eudcApi.model.Location;

import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

//Created by rico on 12.06.16
public class LocationDAOTest extends DatabaseTestBase {
	
	@Inject
    private LocationDAO locationDAO;

    @Test
    public void findAll() {
        List<Location> Locations = locationDAO.findAll();

        assertValidLocation(Locations.get(0));
        assertValidLocation(Locations.get(1));
    }

    @Test
    public void saveLocation() {
        Location Location = new Location();
        Location.setName("Tallinn Harbour");
        Location.setLng("24.767353");
        Location.setLat("59.443634");

        int initialSize = locationDAO.findAll().size();

        locationDAO.saveLocation(Location);

        assertEquals(initialSize + 1, locationDAO.findAll().size());
    }

    private void assertValidLocation(Location Location) {
        assertNotNull(Location.getId());
        if (Location.getId() == 1) {
            assertEquals("Tallinn Harbour", Location.getName());
        } else if (Location.getId() == 2) {
            assertEquals("Tallinn University of Technology", Location.getName());
        } else {
            fail("Location with unexpected id.");
        }
    }
}
