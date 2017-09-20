package com.androidizate.climapp.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Marcos on 18/9/2017.
 */

public class DayInfo {

    @SerializedName("dt")
    private long date;
    private Main main;
    private List<Weather> weather = null;
    private Clouds clouds;
    private Wind wind;
    @SerializedName("rain")
    private WeatherCondition rain;
    @SerializedName("snow")
    private WeatherCondition snow;
    private Sys sys;
    @SerializedName("dt_txt")
    private String forecastTime;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
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

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(String forecastTime) {
        this.forecastTime = forecastTime;
    }
}
