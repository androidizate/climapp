package com.androidizate.climapp.domain.interactors.actual;

import com.androidizate.climapp.data.db.model.ActualWeather;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */

public interface ActualWeatherInteractor {

    Observable<Boolean> isActualWeatherEmpty(long cityId);

    Observable<Boolean> saveActualWeather(ActualWeather actualWeather);

    Observable<Map<Long, ActualWeather>> getActualWeather(long cityId, Map<String, String> options);
}
