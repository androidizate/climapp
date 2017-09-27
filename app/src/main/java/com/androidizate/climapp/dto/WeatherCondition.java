package com.androidizate.climapp.dto;

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