package org.eezer.appbackend;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;


public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
              req.response()
                .putHeader("content-type", "text/plain")
                .end("Hello from Vert.x!");
            }).listen(8080);
        System.out.println("HTTP server started on port 8080");
    }

    private Router getRouter () {

        Router router = Router.router(vertx);
        /*router.route(API_PATH_EXPORT).get(adminRoutes.export);
        router.route(API_PATH_LOGIN).post(adminRoutes.login);
*/
/* Set up root transport routes */
  /*      router.route(API_PATH_STORE).post(transportRoutes.storeTransport);
        router.route(API_PATH_ALL).get(transportRoutes.getAll);
        router.route(API_PATH_COORDS + ":id").get(transportRoutes.getCoordinates);
        router.route(API_PATH_REMOVE + ":id").delete(transportRoutes.removeTransport);
*/
/* Set up User routes */
  /*      router.route(API_PATH_ADD_USER).post(userRoutes.addUser);
        router.route(API_PATH_DELETE_USERS).delete(userRoutes.deleteUser);
        router.route(API_PATH_GET_USERS).get(userRoutes.getUsers);
*/
/* Set up vehicle routes */
  /*      router.route(API_PATH_ADD_VEHICLE).post(vehicleRoutes.addVehicle);
        router.route(API_PATH_DELETE_VEHICLE).delete(vehicleRoutes.deleteVehicle);
        router.route(API_PATH_GET_VEHICLES).get(vehicleRoutes.getVehicles);*/

        return router;
    }
}
