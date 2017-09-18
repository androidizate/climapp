package com.androidizate.climapp.api;


import com.androidizate.climapp.dao.WeatherInfo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Marcos on 18/9/2017.
 */

public interface RestApi {

    @GET
    Call<List<WeatherInfo>> getWeatherInfo(@QueryMap(encoded=true) Map<String, String> options);
}
