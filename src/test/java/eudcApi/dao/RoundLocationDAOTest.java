package eudcApi.dao;

import eudcApi.common.test.DatabaseTestBase;
import eudcApi.model.RoundLocation;

import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

//Created by karl on 18.04.17
public class RoundLocationDAOTest extends DatabaseTestBase {
	
	@Inject
    private RoundLocationDAO roundLocationDAO;

    @Test
    public void findAll() {
        List<RoundLocation> roundLocations = roundLocationDAO.findAll();

        assertValidRoundLocation(roundLocations.get(0));
        assertValidRoundLocation(roundLocations.get(1));
    }

    @Test
    public void saveLocation() {
        RoundLocation RoundLocation = new RoundLocation();
        RoundLocation.setName("U03");
        RoundLocation.setImgurl("http://i.imgur.com/h1YP8oA.jpg");


        int initialSize = roundLocationDAO.findAll().size();

        roundLocationDAO.saveRoundLocation(RoundLocation);

        assertEquals(initialSize + 1, roundLocationDAO.findAll().size());
    }

    private void assertValidRoundLocation(RoundLocation RoundLocation) {
        assertNotNull(RoundLocation.getId());
        if (RoundLocation.getId() == 1) {
            assertEquals("U03", RoundLocation.getName());
        } else if (RoundLocation.getId() == 2) {
            assertEquals("U04", RoundLocation.getName());
        } else {
            fail("Location with unexpected id.");
        }
    }
}
