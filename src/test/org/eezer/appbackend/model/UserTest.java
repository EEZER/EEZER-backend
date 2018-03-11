package org.eezer.appbackend.model;

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
public class UserTest {

    private JsonObject jsonObject = new JsonObject()
        .put("username", "Username 123")
        .put("password", "HASHED PASSWD")
        .put("role", "ADMIN")
        .put("realName", "Gustaf")
        .put("phone", "+1111111")
        .put("email", "email@email.com")
        .put("organization", "Paddle Nose Studios")
        .put("other", "Test with other");

    @Before
    public void setUp(TestContext context) throws Exception {
    }

    @After
    public void tearDown(TestContext context) throws Exception {
    }

    /**
     * Mapping jsonObject to User
     * @param context Context
     */
    @Test
    public void mapToTest(TestContext context) {
        try {
            User user = jsonObject.mapTo(User.class);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    /**
     * Tests with missing username.
     * @param context Context
     */
    @Test
    public void mapToTestFail(TestContext context) {
        try {
            JsonObject jsonObject = new JsonObject()
                .put("password", "HASHED PASSWD")
                .put("role", "ADMIN")
                .put("realName", "Gustaf")
                .put("phone", "+1111111")
                .put("email", "email@email.com")
                .put("organization", "Paddle Nose Studios")
                .put("other", "Test with other");
            User user = jsonObject.mapTo(User.class);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Tests with short username.
     * @param context Context
     */
    @Test
    public void mapToTestFailShortUsername(TestContext context) {
        try {
            JsonObject jsonObject = new JsonObject()
                .put("username", "1234")
                .put("password", "HASHED PASSWD")
                .put("role", "ADMIN")
                .put("realName", "Gustaf")
                .put("phone", "+1111111")
                .put("email", "email@email.com")
                .put("organization", "Paddle Nose Studios")
                .put("other", "Test with other");
            User user = jsonObject.mapTo(User.class);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    /**
     * Maps jsonObject to User and converts back to Json.
     * @param context Context
     * @throws Exception Exception
     */
    @Test
    public void toJson(TestContext context) throws Exception {
        try {
            User user = jsonObject.mapTo(User.class);
            assertTrue(user.toJson().equals(jsonObject));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}
