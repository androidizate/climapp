package com.androidizate.climapp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author Marcos Toranzo.
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
