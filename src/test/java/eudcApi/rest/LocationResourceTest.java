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
import eudcApi.model.Location;

// Created by rico on 12.06.16
public class LocationResourceTest extends ResourceIntegrationTestBase {
	
	@Test
    public void addLocation() {
        Location location = new Location();
        location.setName("Tallinn Harbour");
        location.setLng("24.767353");
        location.setLat("59.443634");

        Response response = doPost("location", Entity.entity(location, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());

    }

    @Test
    public void getAll() {
        Response response = doGet("location");

        List<Location> locations = response.readEntity(new GenericType<List<Location>>() {
        });
        
        assertNotNull(locations.get(0));
        assertNotNull(locations.get(1));
    }

}
