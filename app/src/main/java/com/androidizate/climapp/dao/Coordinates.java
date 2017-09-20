package com.androidizate.climapp.dao;

import com.google.gson.annotations.SerializedName;

/**
 * @author Marcos Toranzo.
 */
public class Coordinates {

    @SerializedName("lat")
    private double latitude;
    @SerializedName("lon")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
