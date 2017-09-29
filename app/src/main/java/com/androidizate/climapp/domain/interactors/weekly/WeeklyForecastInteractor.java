package com.androidizate.climapp.domain.interactors.weekly;

import com.androidizate.climapp.data.db.model.DailyWeather;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */

public interface WeeklyForecastInteractor {

    Observable<Boolean> isDailyWeatherEmpty(long cityId);

    Observable<Boolean> saveDailyWeather(DailyWeather dailyWeather);

    Observable<Boolean> saveDailyWeatherList(List<DailyWeather> dailyWeatherList);

    Observable<Map<Long, List<DailyWeather>>> getDailyWeatherForCity(long cityId, Map<String, String> options);

    Observable<Map<Long, List<DailyWeather>>> refreshDailyWeatherForCity(Map<String, String> options);

    Observable<Map<Long, List<DailyWeather>>> getDailyWeatherForAllCities();

    Observable<Map<Long, List<DailyWeather>>> getWeeklyForecast(Map<String, String> options);
}
