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
import eudcApi.model.RoundLocation;

// Created by karl on 18.04.17
public class RoundLocationResourceTest extends ResourceIntegrationTestBase {
	
	@Test
    public void addRoundLocation() {
        RoundLocation roundlocation = new RoundLocation();
        roundlocation.setName("U03");
        roundlocation.setImgurl("http://i.imgur.com/h1YP8oA.jpg");

        Response response = doPost("roundlocation", Entity.entity(roundlocation, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

    }

    @Test
    public void getAll() {
        Response response = doGet("roundlocation");

        List<RoundLocation> roundlocations = response.readEntity(new GenericType<List<RoundLocation>>() {
        });
        
        assertNotNull(roundlocations.get(0));
        assertNotNull(roundlocations.get(1));
    }

}
