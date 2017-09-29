package com.androidizate.climapp.api;

import com.androidizate.climapp.dto.ActualWeatherInfo;
import com.androidizate.climapp.dto.WeatherInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.QueryMap;

/**
 * @author Marcos Toranzo.
 */
public class RestApiClient implements RestApi {
    private static final String BASE_URL = "http://api.openweathermap.org/";

    @Override
    public Call<WeatherInfo> getWeeklyForecast(@QueryMap Map<String, String> options) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RestApi.class).getWeeklyForecast(options);
    }

    @Override
    public Call<WeatherInfo> getActualWeather(@QueryMap(encoded = true) Map<String, String> options) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RestApi.class).getActualWeather(options);
    }
}
