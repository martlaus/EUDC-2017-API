package eudcApi.service;

import eudcApi.dao.LocationDAO;
import eudcApi.model.Location;

import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

// Created by karl on 12.06.16
@RunWith(EasyMockRunner.class)
public class LocationServiceTest {

	@TestSubject
    private LocationService locationService = new LocationService();

    @Mock
    private LocationDAO locationDAO;

    @Test
    public void saveLocation() {
        Location location = new Location();
        location.setName("Tallinn Harbour");
        Capture<Location> capturedLocation = newCapture();
        expectCreateLocation(capturedLocation);

        replay(locationDAO);

        Location returnedLocation = locationService.saveLocation(location);

        verify(locationDAO);

        assertNotNull(returnedLocation);
        assertNotEquals("name", returnedLocation.getName());

    }

    private void expectCreateLocation(Capture<Location> capturedLocation) {
        expect(locationDAO.saveLocation(EasyMock.capture(capturedLocation)))
                .andAnswer(capturedLocation::getValue);
    }
}
