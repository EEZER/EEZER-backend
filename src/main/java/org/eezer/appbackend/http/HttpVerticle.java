package org.eezer.appbackend.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.serviceproxy.ServiceProxyBuilder;
import org.eezer.appbackend.database.EezerDBService;
import org.eezer.appbackend.model.Transport;

public class HttpVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpVerticle.class);
    private EezerDBService eezerDBService;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        ServiceProxyBuilder builder = new ServiceProxyBuilder(vertx).setAddress(EezerDBService.eezerdb_address);
        eezerDBService = builder.build(EezerDBService.class);

        Router router = getRouter();

        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(8080, ar -> {
                if (ar.succeeded()) {
                    LOGGER.info("HTTP server started on port 8080");
                    startFuture.complete();
                } else {
                    LOGGER.fatal("Error starting HTTP", ar.cause());
                    startFuture.fail(ar.cause());
                }
            });
    }

    private Router getRouter () {

        Router router = Router.router(vertx);

        router.post().handler(BodyHandler.create());
        // router.get(RouteConstants.API_PATH_EXPORT).handler(adminRoutes.export);
        // router.post(RouteConstants.API_PATH_LOGIN).post(adminRoutes.login);

/* Set up root transport routes */
        router.post(RouteConstants.API_PATH_STORE).handler(this::postTransport);
        router.get(RouteConstants.API_PATH_ALL).handler(this::getAllTransports);
        router.get(RouteConstants.API_PATH_COORDS + ":id").get(transportRoutes.getCoordinates);
        router.delete(RouteConstants.API_PATH_REMOVE + ":id").delete(transportRoutes.removeTransport);

/* Set up User routes */
        // router.post(RouteConstants.API_PATH_ADD_USER).post(userRoutes.addUser);
        // router.delete(RouteConstants.API_PATH_DELETE_USERS).delete(userRoutes.deleteUser);
        // router.get(RouteConstants.API_PATH_GET_USERS).get(userRoutes.getUsers);

/* Set up vehicle routes */
        // router.post(RouteConstants.API_PATH_ADD_VEHICLE).post(vehicleRoutes.addVehicle);
        // router.delete(RouteConstants.API_PATH_DELETE_VEHICLE).delete(vehicleRoutes.deleteVehicle);
        // router.get(RouteConstants.API_PATH_GET_VEHICLES).get(vehicleRoutes.getVehicles);

        return router;
    }

    private void postTransport(RoutingContext routingContext) {
        try {
            Transport transport = routingContext.getBodyAsJson().mapTo(Transport.class);
            eezerDBService.postTransport(transport.toJson(), res -> {
                if (res.succeeded()) {
                    LOGGER.info("Transport created, replying to client");
                    routingContext.response().setStatusCode(201).putHeader("Content-Type", "application/json").end(res.result().encode());
                } else {
                    LOGGER.error("Error creating transport", res.cause());
                    routingContext.response().setStatusCode(500).end(new JsonObject().encode());
                }
            });
        } catch (Exception e) {
            LOGGER.error("Validation error ", e);
            // TODO: 2018-03-28 Reply with proper error message
            routingContext.response().setStatusCode(400).end(new JsonObject().encode());
        }
    }

    private void getAllTransports (RoutingContext routingContext) {
        eezerDBService.getAllTransports(res -> {
            if (res.succeeded()) {
                LOGGER.info("Getting all transports, replying to client");
                routingContext.response().setStatusCode(200).end(res.result().encode());
            } else {
                LOGGER.error("Error getting all transports", res.cause());
                routingContext.response().setStatusCode(500).end(new JsonObject().encode());
            }
        });
    }
}
