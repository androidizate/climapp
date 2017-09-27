package com.androidizate.climapp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author Marcos Toranzo.
 */
public class Main {

    @SerializedName("temp")
    private double temperature;
    @SerializedName("temp_min")
    private double minimumTemperature;
    @SerializedName("temp_max")
    private double maxTemperature;
    private double pressure;
    @SerializedName("sea_level")
    private double seaLevel;
    @SerializedName("grnd_level")
    private double groundLevel;
    private int humidity;
    @SerializedName("temp_kf")
    private double tempKf;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(double groundLevel) {
        this.groundLevel = groundLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTempKf() {
        return tempKf;
    }

    public void setTempKf(double tempKf) {
        this.tempKf = tempKf;
    }

}
