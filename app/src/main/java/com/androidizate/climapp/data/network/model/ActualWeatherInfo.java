package com.androidizate.climapp.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Andres Oller
 */

public class ActualWeatherInfo {

    private long id;
    @SerializedName("coord")
    private Coordinates coordinates;
    private Weather weather;
    private String base;
    private Main main;
    private long visibility;
    private Wind wind;
    private Clouds clouds;
    @SerializedName("rain")
    private WeatherCondition rain;
    @SerializedName("snow")
    private WeatherCondition snow;
    @SerializedName("dt")
    private long date;
    private Sys sys;
    private String name;
    private String cod;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public WeatherCondition getRain() {
        return rain;
    }

    public void setRain(WeatherCondition rain) {
        this.rain = rain;
    }

    public WeatherCondition getSnow() {
        return snow;
    }

    public void setSnow(WeatherCondition snow) {
        this.snow = snow;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
