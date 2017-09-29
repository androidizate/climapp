package com.androidizate.climapp.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author Marcos Toranzo.
 */
public class City {

    private long id;
    private String name;
    @SerializedName("coord")
    private Coordinates coordinates;
    private String country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}