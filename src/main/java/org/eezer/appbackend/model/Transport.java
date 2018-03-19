package org.eezer.appbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonObject;

import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Transport implements Model {

    public static final String COLLECTION = "Transport";

    // TODO: 2018-03-11 Add created time fields

    private String transportId;
    private String driverId;
    private String vehicleId;
    private String passengerName;
    private String passengerPhone;
    private String gender;
    private String reason;
    private List<Coordinates> coordinates;
    private long distance;
    private long duration;
    private String started;
    private String ended;

    @JsonCreator
    public Transport(@JsonProperty(value = "transportId", required = true) String transportId,
                     @JsonProperty(value = "driverId", required = true) String driverId,
                     @JsonProperty(value = "vehicleId", required = true) String vehicleId,
                     @JsonProperty(value = "distance", required = true) long distance,
                     @JsonProperty(value = "duration", required = true) long duration,
                     @JsonProperty(value = "started", required = true) String started,
                     @JsonProperty(value = "ended", required = true) String ended){
        setTransportId(transportId);
        setDriverId(driverId);
        setVehicleId(vehicleId);
        setDistance(distance);
        setDuration(duration);
        setStarted(started);
        setEnded(ended);
    }

    public String getTransportId() {
        return transportId;
    }

    public void setTransportId(String transportId) {
        if (transportId.length() < 10) throw new IllegalArgumentException("transportId must be at least 10 chars");
        this.transportId = transportId;
    }
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        if (driverId.length() < 5) throw new IllegalArgumentException("driverId must be at least 5 chars");
        this.driverId = driverId;
    }


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        if (vehicleId.length() < 2) throw new IllegalArgumentException("vehicleId must be at least 2 chars");
        this.vehicleId = vehicleId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    @Override
    public JsonObject toJson() {
        return new JsonObject()
            .put("transportId", transportId)
            .put("driverId", driverId)
            .put("vehicleId", vehicleId)
            .put("passengerName", passengerName)
            .put("passengerPhone", passengerPhone)
            .put("gender", gender)
            .put("reason", reason)
            .put("coordinates", Coordinates.toJsonArray(coordinates))
            .put("distance", distance)
            .put("duration", duration)
            .put("started", started)
            .put("ended", ended);
    }
}
