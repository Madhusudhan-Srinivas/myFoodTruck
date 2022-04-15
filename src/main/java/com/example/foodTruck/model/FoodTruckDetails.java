package com.example.foodTruck.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruckDetails {

    @JsonProperty("location")
    private String location;
    @JsonProperty("applicant")
    private String applicant;
    @JsonProperty("starttime")
    private String starttime;
    @JsonProperty("endtime")
    private String endtime;
    @JsonProperty("start24")
    private String start24;
    @JsonProperty("end24")
    private String end24;

    public FoodTruckDetails() {
    }

    public FoodTruckDetails(String location, String applicant, String starttime, String endtime, String start24, String end24) {
        this.location = location;
        this.applicant = applicant;
        this.starttime = starttime;
        this.endtime = endtime;
        this.start24 = start24;
        this.end24 = end24;
    }

    public String getLocation() {
        return location;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public String getStart24() {
        return start24;
    }

    public String getEnd24() {
        return end24;
    }
}