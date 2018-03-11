package org.eezer.appbackend.model;

import io.vertx.core.json.JsonObject;

interface Model {
    /**
     * Converts class to Vert.X Json object.
     * @return Json representation of class
     */
    @SuppressWarnings("SpellCheckingInspection")
    JsonObject toJson();
}
