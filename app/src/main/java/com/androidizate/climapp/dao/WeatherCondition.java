package com.androidizate.climapp.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marcos on 18/9/2017.
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