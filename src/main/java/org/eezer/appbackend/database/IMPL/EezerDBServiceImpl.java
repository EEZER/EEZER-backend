package org.eezer.appbackend.database.IMPL;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;
import org.eezer.appbackend.database.EezerDBService;
import org.eezer.appbackend.model.Transport;

public class EezerDBServiceImpl implements EezerDBService {

    private Logger LOGGER = LoggerFactory.getLogger(EezerDBServiceImpl.class);
    private MongoClient client;

    public EezerDBServiceImpl(MongoClient client){
        this.client = client;
    }

    @Override
    public EezerDBService postTransport(JsonObject _transport, Handler<AsyncResult<JsonObject>> resultHandler) {
        Transport transport = _transport.mapTo(Transport.class);
        client.save(Transport.COLLECTION, transport.toJson(), res -> {
            if (res.succeeded()) {
                LOGGER.info("Succeeded saving new transport to database _id:" + res.result());
                resultHandler.handle(Future.succeededFuture(new JsonObject()
                    .put("transportId", transport.getTransportId())
                    .put("distance", transport.getDistance())
                    .put("duration", transport.getDuration())));
            } else {
                LOGGER.error("Error saving transport to database", res.cause());
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
        return this;
    }

    @Override
    public EezerDBService deleteTransport(String transportId, Handler<AsyncResult<Void>> resultHandler) {
        client.removeDocument(Transport.COLLECTION, new JsonObject().put("transportId", transportId), res -> {
            if (res.succeeded()) {
                if (res.result().getRemovedCount() == 1) {
                    LOGGER.info("Deleted transport: " + transportId);
                    resultHandler.handle(Future.succeededFuture());
                } else {
                    LOGGER.error("Error deleting transport, not found: " + transportId);
                    resultHandler.handle(Future.failedFuture(new Exception("Not Found")));
                }
            } else {
                LOGGER.error("Error removing transport from database", res.cause());
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
        return this;
    }

    @Override
    public EezerDBService getAllTransports(Handler<AsyncResult<JsonArray>> resultHandler) {
        client.findWithOptions(Transport.COLLECTION, new JsonObject(), new FindOptions().setFields(Transport.fieldsNoCoordinates()), res -> {
            if(res.succeeded()) {
                LOGGER.info("Get all transports");
                resultHandler.handle(Future.succeededFuture(new JsonArray(res.result())));
            } else {
                LOGGER.error("Error getting all transports", res.cause());
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
        return this;
    }

    @Override
    public EezerDBService getCoordinates(String transportId, Handler<AsyncResult<JsonArray>> resultHandler) {
        client.findOne(Transport.COLLECTION, new JsonObject().put("transportId", transportId), new JsonObject().put("coordinates", 1), res -> {
            if (res.succeeded()) {
                LOGGER.info("Get coordinates for transport: " + transportId);
                JsonArray coordinates = res.result().getJsonArray("coordinates");
                // TODO: 2018-03-28 This is for testing purposes only, delete when confirmed working.
                LOGGER.info("Coordinates \n" + coordinates.encodePrettily());
                resultHandler.handle(Future.succeededFuture(coordinates));
            } else {
                LOGGER.error("Error getting coordinates for transport: " + transportId, res.cause());
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
        return this;
    }
}
