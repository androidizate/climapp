package com.androidizate.climapp.domain.interactors.weekly;

import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.data.db.repository.weekly.DailyWeatherRepository;
import com.androidizate.climapp.data.network.NetworkRepository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */
public class WeeklyForecastInteractorImpl implements WeeklyForecastInteractor {

    NetworkRepository networkRepository;
    DailyWeatherRepository dailyWeatherRepository;

    @Inject
    public WeeklyForecastInteractorImpl(NetworkRepository networkRepository, DailyWeatherRepository dailyWeatherRepository) {
        this.networkRepository = networkRepository;
        this.dailyWeatherRepository = dailyWeatherRepository;
    }

    @Override
    public Observable<Boolean> isDailyWeatherEmpty(long cityId) {
        return dailyWeatherRepository.isDailyWeatherEmpty(cityId);
    }

    @Override
    public Observable<Boolean> saveDailyWeather(DailyWeather dailyWeather) {
        return dailyWeatherRepository.saveDailyWeather(dailyWeather);
    }

    @Override
    public Observable<Boolean> saveDailyWeatherList(List<DailyWeather> dailyWeatherList) {
        return dailyWeatherRepository.saveDailyWeatherList(dailyWeatherList);
    }

    @Override
    public Observable<Map<Long, List<DailyWeather>>> getDailyWeatherForCity(long cityId, Map<String, String> options) {
        if (dailyWeatherRepository.dailyWeatherExists(cityId)) {
            return dailyWeatherRepository.getDailyWeatherForCity(cityId);
        } else {
            return refreshDailyWeatherForCity(options);
        }
    }

    @Override
    public Observable<Map<Long, List<DailyWeather>>> refreshDailyWeatherForCity(Map<String, String> options) {
        return networkRepository.getWeeklyForecast(options);
    }

    @Override
    public Observable<Map<Long, List<DailyWeather>>> getDailyWeatherForAllCities() {
        return dailyWeatherRepository.getDailyWeatherForAllCities();
    }

    @Override
    public Observable<Map<Long, List<DailyWeather>>> getWeeklyForecast(Map<String, String> options) {
        return networkRepository.getWeeklyForecast(options)
                .startWith(dailyWeatherRepository.getDailyWeatherForCity(Long.parseLong(options.get("cityId"))));
    }
}
