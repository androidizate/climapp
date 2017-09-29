package com.androidizate.climapp.data.network;

import com.androidizate.climapp.data.db.model.ActualWeather;
import com.androidizate.climapp.data.db.model.DailyWeather;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.QueryMap;

/**
 * @author Andres Oller
 */

public interface NetworkRepository {

    public Observable<Map<Long, List<DailyWeather>>> getWeeklyForecast(@QueryMap(encoded = true) Map<String, String> options);

    public Observable<Map<Long, ActualWeather>> getActualWeather(@QueryMap(encoded = true) Map<String, String> options);

}
