package com.androidizate.climapp.data.db.repository.weekly;

import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.data.network.model.WeatherInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */

public interface DailyWeatherRepository {

    Observable<Boolean> isDailyWeatherEmpty(long cityId);

    Observable<Boolean> saveDailyWeather(DailyWeather dailyWeather);

    Observable<Boolean> saveDailyWeatherList(List<DailyWeather> dailyWeatherList);

    Observable<Map<Long, List<DailyWeather>>> getDailyWeatherForCity(long cityId);

    Observable<Map<Long, List<DailyWeather>>> getDailyWeatherForAllCities();

    Map<Long, List<DailyWeather>> saveDailyWeather(WeatherInfo weatherInfo);

    boolean dailyWeatherExists(long cityId);
}
