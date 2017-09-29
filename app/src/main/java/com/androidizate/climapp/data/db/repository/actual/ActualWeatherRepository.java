package com.androidizate.climapp.data.db.repository.actual;

import com.androidizate.climapp.data.db.model.ActualWeather;
import com.androidizate.climapp.data.network.model.ActualWeatherInfo;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */

public interface ActualWeatherRepository {

    Observable<Boolean> isActualWeatherEmpty(long cityId);

    Map<Long, ActualWeather> saveActualWeather(ActualWeatherInfo actualWeatherInfo);

    Observable<Map<Long, ActualWeather>> getActualWeather(long cityId);
}
