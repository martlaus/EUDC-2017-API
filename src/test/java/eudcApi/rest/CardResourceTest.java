package eudcApi.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;

import eudcApi.common.test.ResourceIntegrationTestBase;
import eudcApi.model.Card;

// Created by karl on 16.02.16
public class CardResourceTest extends ResourceIntegrationTestBase {

	@Test
    public void addCard() {
        Card card = new Card();
        card.setTitle("Lorem Ipsum");
        card.setDescription("Sed omnium volumus voluptua te.");
        //card.setCreated(null);
        //card.setId(null);

        Response response = doPost("card", Entity.entity(card, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

    }

    @Test
    public void getAll() {
        Response response = doGet("card");

        List<Card> cards = response.readEntity(new GenericType<List<Card>>() {
        });
        
        assertNotNull(cards.get(0));
        assertNotNull(cards.get(1));
    }
	
}
