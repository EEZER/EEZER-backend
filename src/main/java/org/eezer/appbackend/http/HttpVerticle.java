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
import org.eezer.appbackend.model.Vehicle;

public class HttpVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpVerticle.class);
    private EezerDBService eezerDBService;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        ServiceProxyBuilder builder = new ServiceProxyBuilder(vertx).setAddress(EezerDBService.eezerdb_address);
        eezerDBService = builder.build(EezerDBService.class);

        Router router = Router.router(vertx);
        router.mountSubRouter("/api", getApiRouter());

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

    private Router getApiRouter () {

        Router router = Router.router(vertx);

        router.post().handler(BodyHandler.create());
        // router.get(RouteConstants.API_PATH_EXPORT).handler(adminRoutes.export);
        // router.post(RouteConstants.API_PATH_LOGIN).post(adminRoutes.login);

/* Set up root transport routes */
        router.post(RouteConstants.API_PATH_STORE).handler(this::postTransport);
        router.get(RouteConstants.API_PATH_ALL).handler(this::getAllTransports);
        router.get(RouteConstants.API_PATH_COORDS + ":id").handler(this::getCoordinates);
        router.delete(RouteConstants.API_PATH_REMOVE + ":id").handler(this::deleteTransport);

/* Set up User routes */
        // router.post(RouteConstants.API_PATH_ADD_USER).post(userRoutes.addUser);
        // router.delete(RouteConstants.API_PATH_DELETE_USERS).delete(userRoutes.deleteUser);
        // router.get(RouteConstants.API_PATH_GET_USERS).get(userRoutes.getUsers);

/* Set up vehicle routes */
        router.post(RouteConstants.API_PATH_ADD_VEHICLE).handler(this::postVehicle);
        router.delete(RouteConstants.API_PATH_DELETE_VEHICLE + ":id").handler(this::deleteVehicle);
        router.get(RouteConstants.API_PATH_GET_VEHICLES).handler(this::getVehicles);

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
            LOGGER.error("Transport validation error ", e);
            // TODO: 2018-03-28 Reply with proper error message
            routingContext.response().setStatusCode(400).end(new JsonObject().encode());
        }
    }

    private void getAllTransports (RoutingContext routingContext) {
        eezerDBService.getAllTransports(res -> {
            if (res.succeeded()) {
                LOGGER.info("Getting all transports, replying to client");
                routingContext.response().setStatusCode(200).putHeader("Content-Type", "application/json").end(res.result().encode());
            } else {
                LOGGER.error("Error getting all transports", res.cause());
                // TODO: 2018-04-06 Reply with proper error message
                routingContext.response().setStatusCode(500).end(new JsonObject().encode());
            }
        });
    }

    private void getCoordinates (RoutingContext routingContext) {
        eezerDBService.getCoordinates(routingContext.request().getParam("id"), res -> {
            if (res.succeeded()) {
                LOGGER.info("Get coordinates succeeded, replying to client");
                routingContext.response().setStatusCode(200).putHeader("Content-Type", "application/json").end(res.result().encode());
            } else {
                LOGGER.error("Error getting coordinates", res.cause());
                // TODO: 2018-04-06 Reply with proper error message
                routingContext.response().setStatusCode(500).end(new JsonObject().encode());
            }
        });
    }

    private void deleteTransport(RoutingContext routingContext) {
        eezerDBService.deleteTransport(routingContext.request().getParam("id"), res -> {
            if (res.succeeded()) {
                LOGGER.info("Remove transport succeeded, replying to client");
                // TODO: 2018-04-06 Check in official documentation for the statuscode and expected reply.
                routingContext.response().setStatusCode(204).putHeader("Content-Type", "application/json").end();
            } else {
                LOGGER.error("Error removing transport", res.cause());
                // TODO: 2018-04-06 Reply with proper error message
                routingContext.response().setStatusCode(500).end(new JsonObject().encode());
            }
        });
    }

    private void postVehicle (RoutingContext routingContext) {
        try {
            Vehicle vehicle = routingContext.getBodyAsJson().mapTo(Vehicle.class);
            eezerDBService.postVehicle(vehicle.toJson(), res -> {
                if (res.succeeded()) {
                    LOGGER.info("Vehicle created, replying to client");
                    routingContext.response().setStatusCode(201).putHeader("Content-Type", "application/json").end(res.result().encode());
                } else {
                    LOGGER.error("Error creating Vehicle", res.cause());
                    routingContext.response().setStatusCode(500).end(new JsonObject().encode());
                }
            });
        } catch (Exception e) {
            LOGGER.error("Vehicle validation error ", e);
            // TODO: 2018-03-28 Reply with proper error message
            routingContext.response().setStatusCode(400).end(new JsonObject().encode());
        }
    }

    private void deleteVehicle (RoutingContext routingContext) {
        eezerDBService.deleteVehicle(routingContext.request().getParam("id"), res -> {
            if (res.succeeded()) {
                LOGGER.info("Remove vehicle succeeded, replying to client");
                // TODO: 2018-04-06 Check in official documentation for the statuscode and expected reply.
                routingContext.response().setStatusCode(204).putHeader("Content-Type", "application/json").end();
            } else {
                LOGGER.error("Error removing vehicle", res.cause());
                // TODO: 2018-04-06 Reply with proper error message
                routingContext.response().setStatusCode(500).end(new JsonObject().encode());
            }
        });
    }

    private void getVehicles (RoutingContext routingContext) {
        eezerDBService.getVehicles(res -> {
            if (res.succeeded()) {
                LOGGER.info("Getting all vehicles, replying to client");
                routingContext.response().setStatusCode(200).putHeader("Content-Type", "application/json").end(res.result().encode());
            } else {
                LOGGER.error("Error getting all vehicles", res.cause());
                // TODO: 2018-04-06 Reply with proper error message
                routingContext.response().setStatusCode(500).end(new JsonObject().encode());
            }
        });
    }
}
