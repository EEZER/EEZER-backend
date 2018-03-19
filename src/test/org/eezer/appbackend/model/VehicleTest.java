package org.eezer.appbackend.model;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(VertxUnitRunner.class)
public class VehicleTest {

    private JsonObject jsonObject = new JsonObject()
        .put("vehicleId", "Vehicle Id")
        .put("country", "Sweden")
        .put("region", "Tyreso")
        .put("organization", "PNS")
        .put("contact", "Gustaf")
        .put("email", "test@test.com")
        .put("phone", "314159")
        .put("address", "1 Hacker way")
        .put("yearOfManufacture", "1971")
        .put("handoverDate", "1994-07-29")
        .put("runningTime", "1338")
        .put("createdTime", "2020-01-29");
    @Before
    public void setUp(TestContext context) throws Exception {
    }

    @After
    public void tearDown(TestContext context) throws Exception {
    }

    /**
     * Mapping jsonObject to Vehicle
     * @param context Context
     */
    @Test
    public void mapToTest(TestContext context) {
        try {
            Vehicle vehicle = jsonObject.mapTo(Vehicle.class);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}
