package com.androidizate.climapp.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * @author Andres Oller
 */
@Entity
public class DailyWeather {

    @Id(autoincrement = true)
    private Long id;

    @Index
    private long cityId;
    @Index
    private long date;

    // Main
    private double temperature;
    private double minimumTemperature;
    private double maxTemperature;
    private double pressure;
    private double seaLevel;
    private double groundLevel;
    private int humidity;
    private double tempKf;

    // Weather
    private long weatherId;
    private String weatherMain;
    private String weatherDescription;
    private String weatherIcon;

    // Wind
    private double speed;
    private double degrees;

    // Clouds
    private int all;

    // Rain
    private double lastThreeHoursRain;

    // Snow
    private double lastThreeHoursSnow;

    @Generated(hash = 327246612)
    public DailyWeather(Long id, long cityId, long date, double temperature, double minimumTemperature, double maxTemperature, double pressure,
                        double seaLevel, double groundLevel, int humidity, double tempKf, long weatherId, String weatherMain, String weatherDescription,
                        String weatherIcon, double speed, double degrees, int all, double lastThreeHoursRain, double lastThreeHoursSnow) {
        this.id = id;
        this.cityId = cityId;
        this.date = date;
        this.temperature = temperature;
        this.minimumTemperature = minimumTemperature;
        this.maxTemperature = maxTemperature;
        this.pressure = pressure;
        this.seaLevel = seaLevel;
        this.groundLevel = groundLevel;
        this.humidity = humidity;
        this.tempKf = tempKf;
        this.weatherId = weatherId;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
        this.speed = speed;
        this.degrees = degrees;
        this.all = all;
        this.lastThreeHoursRain = lastThreeHoursRain;
        this.lastThreeHoursSnow = lastThreeHoursSnow;
    }

    @Generated(hash = 1791403155)
    public DailyWeather() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCityId() {
        return this.cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getMinimumTemperature() {
        return this.minimumTemperature;
    }

    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public double getMaxTemperature() {
        return this.maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getPressure() {
        return this.pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSeaLevel() {
        return this.seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGroundLevel() {
        return this.groundLevel;
    }

    public void setGroundLevel(double groundLevel) {
        this.groundLevel = groundLevel;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTempKf() {
        return this.tempKf;
    }

    public void setTempKf(double tempKf) {
        this.tempKf = tempKf;
    }

    public long getWeatherId() {
        return this.weatherId;
    }

    public void setWeatherId(long weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherMain() {
        return this.weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return this.weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return this.weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDegrees() {
        return this.degrees;
    }

    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    public int getAll() {
        return this.all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public double getLastThreeHoursRain() {
        return this.lastThreeHoursRain;
    }

    public void setLastThreeHoursRain(double lastThreeHoursRain) {
        this.lastThreeHoursRain = lastThreeHoursRain;
    }

    public double getLastThreeHoursSnow() {
        return this.lastThreeHoursSnow;
    }

    public void setLastThreeHoursSnow(double lastThreeHoursSnow) {
        this.lastThreeHoursSnow = lastThreeHoursSnow;
    }

}
