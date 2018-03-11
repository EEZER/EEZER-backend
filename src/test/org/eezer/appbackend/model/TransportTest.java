package org.eezer.appbackend.model;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
@RunWith(VertxUnitRunner.class)
public class TransportTest {

    private JsonObject jsonObject = new JsonObject()
        .put("transportId", "transportId 123456789")
        .put("driverId", "driverId 123456789")
        .put("vehicleId", "vehicleId 123456789")
        .put("passengerName", "passengerName 123456789")
        .put("passengerPhone", "passengerPhone 123456789")
        .put("gender", "gender")
        .put("reason", "reason")
        .put("coordinates", new JsonArray()
            .add(new JsonObject()
                .put("lat", 1)
                .put("lng", 12)
            )
            .add(new JsonObject()
                .put("lat", 1)
                .put("lng", 12)
            )
        )
        .put("distance", 123)
        .put("duration", 123)
        .put("started", "started")
        .put("ended", "started");

    @Before
    public void setUp(TestContext context) throws Exception {
    }

    @After
    public void tearDown(TestContext context) throws Exception {
    }

    /**
     * Tests with missing transportId
     * @param context context
     * @throws Exception Exception
     */
    @Test
    public void mapToTestFail(TestContext context) throws Exception {
        try {
            JsonObject jsonObject = new JsonObject()
                .put("driverId", "driverId 123456789")
                .put("vehicleId", "vehicleId 123456789")
                .put("passengerName", "passengerName 123456789")
                .put("passengerPhone", "passengerPhone 123456789")
                .put("gender", "gender")
                .put("reason", "reason")
                .put("coordinates", new JsonArray()
                    .add(new JsonObject()
                        .put("lat", 1)
                        .put("lng", 12)
                    )
                    .add(new JsonObject()
                        .put("lat", 1)
                        .put("lng", 12)
                    )
                )
                .put("distance", 123)
                .put("duration", 123)
                .put("started", "started")
                .put("ended", "started");
            Transport transport = jsonObject.mapTo(Transport.class);
            assertTrue(false);
        } catch (Exception e) {
            // Expected to fail
            assertTrue(true);

        }
    }

    /**
     * Tests with short transportId
     * @param context context
     * @throws Exception Exception
     */
    @Test
    public void mapToTestFailShortTransportId(TestContext context) throws Exception {
        try {
            JsonObject jsonObject = new JsonObject()
                .put("transportId", "123456789")
                .put("driverId", "driverId 123456789")
                .put("vehicleId", "vehicleId 123456789")
                .put("passengerName", "passengerName 123456789")
                .put("passengerPhone", "passengerPhone 123456789")
                .put("gender", "gender")
                .put("reason", "reason")
                .put("coordinates", new JsonArray()
                    .add(new JsonObject()
                        .put("lat", 1)
                        .put("lng", 12)
                    )
                    .add(new JsonObject()
                        .put("lat", 1)
                        .put("lng", 12)
                    )
                )
                .put("distance", 123)
                .put("duration", 123)
                .put("started", "started")
                .put("ended", "started");
            Transport transport = jsonObject.mapTo(Transport.class);
            assertTrue(false);
        } catch (Exception e) {
            // Expected to fail
            assertTrue(true);

        }
    }

    /**
     * Maps jsonObject to Transport.
     * @param context Context
     * @throws Exception Exception
     */
    @Test
    public void mapToTest(TestContext context) throws Exception {
        try {
            Transport transport = jsonObject.mapTo(Transport.class);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);

        }
    }

    /**
     * Maps jsonObject to Transport and converts back to Json.
     * @param context Context
     * @throws Exception Exception
     */
    @Test
    public void toJsonTest(TestContext context) throws Exception {
        try {
            Transport transport = jsonObject.mapTo(Transport.class);
            JsonObject jsonTransport = transport.toJson();
            assertTrue(jsonTransport.equals(jsonObject));
            assertTrue(jsonTransport.fieldNames().containsAll(jsonObject.fieldNames()));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);

        }
    }

}
