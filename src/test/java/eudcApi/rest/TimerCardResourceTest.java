package eudcApi.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.junit.Test;

import eudcApi.common.test.ResourceIntegrationTestBase;
import eudcApi.model.TimerCard;

// Created by karl on 05.04.16
public class TimerCardResourceTest extends ResourceIntegrationTestBase {

	@Test
    public void addTimerCard() {
		
		DateTime endDate = new DateTime(2017, 3, 5, 0, 0);
		
        TimerCard timercard = new TimerCard();
        timercard.setTitle("Round start notice");
        timercard.setDescription("Your round will start in: ");
        timercard.setEndDate(endDate);

        Response response = doPost("timercard", Entity.entity(timercard, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

    }

    @Test
    public void getAll() {
        Response response = doGet("timercard");

        List<TimerCard> timercards = response.readEntity(new GenericType<List<TimerCard>>() {
        });

        assertNotNull(timercards.get(0));

    }
	
}
