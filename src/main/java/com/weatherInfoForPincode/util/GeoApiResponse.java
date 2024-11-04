package com.weatherInfoForPincode.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoApiResponse {
    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lon")
    private double lon;

    // Getters and Setters
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}

