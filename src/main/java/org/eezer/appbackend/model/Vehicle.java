package org.eezer.appbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonObject;

public class Vehicle implements Model{
    private String vehicleId;
    private String country;
    private String region;
    private String organization;
    private String contact;
    private String phone;
    private String email;
    private String address;
    private String yearOfManufacture;
    private String handoverDate;
    private String runningTime;
    private String createdTime;


    @JsonCreator
    public Vehicle (@JsonProperty(value = "vehicleId", required = true) String vehicleId,
                    @JsonProperty(value = "country", required = true) String country,
                    @JsonProperty(value = "region", required = true) String region,
                    @JsonProperty(value = "organization", required = true) String organization,
                    @JsonProperty(value = "contact", required = true) String contact,
                    @JsonProperty(value = "phone", required = true) String phone,
                    @JsonProperty(value = "email", required = true) String email,
                    @JsonProperty(value = "address", required = true) String address,
                    @JsonProperty(value = "yearOfManufacture", required = true) String yearOfManufacture,
                    @JsonProperty(value = "handoverDate", required = true) String handoverDate,
                    @JsonProperty(value = "runningTime", required = true) String runningTime){
        setVehicleId(vehicleId);
        setCountry(country);
        setRegion(region);
        setOrganization(organization);
        setContact(contact);
        setPhone(phone);
        setEmail(email);
        setAddress(address);
        setYearOfManufacture(yearOfManufacture);
        setHandoverDate(handoverDate);
        setRunningTime(runningTime);
    }
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getHandoverDate() {
        return handoverDate;
    }

    public void setHandoverDate(String handoverDate) {
        this.handoverDate = handoverDate;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
