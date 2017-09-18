package com.androidizate.climapp.api;

import com.androidizate.climapp.dao.WeatherInfo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.QueryMap;

/**
 * Created by Marcos on 18/9/2017.
 */

public class RestApiClient implements RestApi {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String APPID = "c6fa2147d92527dd670150c95ae74ed4";

    @Override
    public Call<List<WeatherInfo>> getWeatherInfo(@QueryMap Map<String, String> options) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        options.put("appid", APPID);
        options.put("lang", "es");
        return retrofit.create(RestApi.class).getWeatherInfo(options);
    }
}
