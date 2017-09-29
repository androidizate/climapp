package com.androidizate.climapp.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Marcos Toranzo.
 */
public class WeatherCondition {

    @SerializedName("3h")
    private double lastThreeHours;

    public double getLastThreeHours() {
        return lastThreeHours;
    }

    public void setLastThreeHours(double lastThreeHours) {
        this.lastThreeHours = lastThreeHours;
    }

}