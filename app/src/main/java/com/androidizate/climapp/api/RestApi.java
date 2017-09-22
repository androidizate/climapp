package com.androidizate.climapp.api;

import com.androidizate.climapp.dao.WeatherInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author Marcos Toranzo.
 */
public interface RestApi {

    @GET("data/2.5/forecast")
    Call<WeatherInfo> getWeatherInfo(@QueryMap(encoded = true) Map<String, String> options);
}
