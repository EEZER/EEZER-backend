package org.eezer.appbackend.database;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.eezer.appbackend.database.IMPL.EezerDBServiceImpl;

@ProxyGen
public interface EezerDBService {

    @GenIgnore
    public static String eezerdb_address = "eezerdb.queue";

    @GenIgnore
    static EezerDBService create (MongoClient client) {
        return new EezerDBServiceImpl(client);
    }

    @GenIgnore
    static EezerDBService createProxy(Vertx vertx, String address) {
        return new EezerDBServiceVertxEBProxy(vertx, address);
    }

    @Fluent
    EezerDBService postTransport (JsonObject transport, Handler<AsyncResult<JsonObject>> resultHandler);

    @Fluent
    EezerDBService deleteTransport (String transportId, Handler<AsyncResult<Void>> resultHandler);

    @Fluent
    EezerDBService getAllTransports (Handler<AsyncResult<JsonArray>> resultHandler);

    @Fluent
    EezerDBService getCoordinates (String transportId, Handler<AsyncResult<JsonArray>> resultHandler);

    @Fluent
    EezerDBService postVehicle (JsonObject vehicle, Handler<AsyncResult<JsonObject>> resultHandler);

    @Fluent
    EezerDBService deleteVehicle (String vehicleId, Handler<AsyncResult<JsonObject>> resultHandler);

    @Fluent
    EezerDBService getVehicles (Handler<AsyncResult<JsonArray>> resultHandler);


}
