package com.androidizate.climapp.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Marcos on 18/9/2017.
 */

public class WeatherInfo {

    @SerializedName("cod")
    private String code;
    private double message;
    @SerializedName("cnt")
    private int count;
    @SerializedName("list")
    private List<DayInfo> dayInfo;
    private City city;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DayInfo> getDayInfo() {
        return dayInfo;
    }

    public void setList(List<DayInfo> dayInfo) {
        this.dayInfo = dayInfo;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
