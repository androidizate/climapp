package com.androidizate.climapp.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marcos on 18/9/2017.
 */

public class Wind {

    private double speed;
    @SerializedName("deg")
    private double degrees;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDegrees() {
        return degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

}
