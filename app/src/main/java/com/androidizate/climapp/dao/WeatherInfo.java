package com.androidizate.climapp.dao;

import java.util.List;

/**
 * Created by Marcos on 18/9/2017.
 */

public class WeatherInfo {

    private String cod;
    private Float message;
    private Integer cnt;
    private java.util.List<com.androidizate.climapp.dao.List> list = null;
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Float getMessage() {
        return message;
    }

    public void setMessage(Float message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.androidizate.climapp.dao.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.androidizate.climapp.dao.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
