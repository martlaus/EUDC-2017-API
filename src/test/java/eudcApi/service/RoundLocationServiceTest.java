package eudcApi.service;

import eudcApi.dao.RoundLocationDAO;
import eudcApi.model.RoundLocation;

import org.easymock.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

// Created by karl on 18.04.17
@RunWith(EasyMockRunner.class)
public class RoundLocationServiceTest {

	@TestSubject
    private RoundLocationService roundLocationService = new RoundLocationService();

    @Mock
    private RoundLocationDAO roundLocationDAO;

    @Test
    public void saveLocation() {
        RoundLocation roundLocation = new RoundLocation();
        roundLocation.setName("U03");
        Capture<RoundLocation> capturedRoundLocation = newCapture();
        expectCreateRoundLocation(capturedRoundLocation);

        replay(roundLocationDAO);

        RoundLocation returnedRoundLocation = roundLocationService.saveRoundLocation(roundLocation);

        verify(roundLocationDAO);

        assertNotNull(returnedRoundLocation);
        assertNotEquals("name", returnedRoundLocation.getName());

    }

    private void expectCreateRoundLocation(Capture<RoundLocation> capturedRoundLocation) {
        expect(roundLocationDAO.saveRoundLocation(EasyMock.capture(capturedRoundLocation)))
                .andAnswer(capturedRoundLocation::getValue);
    }
}
