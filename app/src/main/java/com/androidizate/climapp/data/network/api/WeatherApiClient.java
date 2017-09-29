package com.androidizate.climapp.data.network.api;

import com.androidizate.climapp.data.network.model.ActualWeatherInfo;
import com.androidizate.climapp.data.network.model.WeatherInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author Andres Oller
 */

public interface WeatherApiClient {

    @GET("data/2.5/forecast")
    Observable<WeatherInfo> getWeeklyForecast(@QueryMap(encoded = true) Map<String, String> options);

    @GET("data/2.5/weather")
    Observable<ActualWeatherInfo> getActualWeather(@QueryMap(encoded = true) Map<String, String> options);
}
