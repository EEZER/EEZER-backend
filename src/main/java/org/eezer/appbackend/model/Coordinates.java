package org.eezer.appbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Coordinates implements Model {
    private long lat;
    private long lng;


    @JsonCreator
    public Coordinates(@JsonProperty(value = "lat", required = true) long lat,
                       @JsonProperty(value = "lng", required = true) long lng){
        setLat(lat);
        setLng(lng);
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLng() {
        return lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    @Override
    public JsonObject toJson() {
        return new JsonObject()
            .put("lat", lat)
            .put("lng", lng);
    }

    static JsonArray toJsonArray(List<Coordinates> coordinatesList) {
        JsonArray coordinatesArray = new JsonArray();
        coordinatesList.forEach(coordinates -> coordinatesArray.add(coordinates.toJson()));
        return coordinatesArray;
    }
}
