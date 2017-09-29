package com.androidizate.climapp.data.network.api;

import com.androidizate.climapp.data.db.model.ActualWeather;
import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.data.db.repository.actual.ActualWeatherRepository;
import com.androidizate.climapp.data.db.repository.weekly.DailyWeatherRepository;
import com.androidizate.climapp.data.network.NetworkRepository;
import com.androidizate.climapp.data.network.model.ActualWeatherInfo;
import com.androidizate.climapp.data.network.model.WeatherInfo;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.QueryMap;

/**
 * @author Marcos Toranzo.
 */
public class RestApiClient implements NetworkRepository {

    DailyWeatherRepository dailyWeatherRepository;
    ActualWeatherRepository actualWeatherRepository;
    WeatherApiClient weatherApiClient;

    @Inject
    RestApiClient(DailyWeatherRepository dailyWeatherRepository, ActualWeatherRepository actualWeatherRepository, WeatherApiClient weatherApiClient) {
        this.dailyWeatherRepository = dailyWeatherRepository;
        this.actualWeatherRepository = actualWeatherRepository;
        this.weatherApiClient = weatherApiClient;
    }

    @Override
    public Observable<Map<Long, List<DailyWeather>>> getWeeklyForecast(@QueryMap Map<String, String> options) {
        return weatherApiClient.getWeeklyForecast(options)
                .subscribeOn(Schedulers.io())
                .flatMap(this::parseWeeklyForecastResponse);
    }

    private Observable<Map<Long, List<DailyWeather>>> parseWeeklyForecastResponse(WeatherInfo weatherInfo) {
        return Observable.just(dailyWeatherRepository.saveDailyWeather(weatherInfo));
    }

    @Override
    public Observable<Map<Long, ActualWeather>> getActualWeather(@QueryMap(encoded = true) Map<String, String> options) {
        return weatherApiClient.getActualWeather(options)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(this::parseActualWeatherForecast);
    }

    private Observable<Map<Long, ActualWeather>> parseActualWeatherForecast(ActualWeatherInfo actualWeatherInfo) {
        return Observable.just(actualWeatherRepository.saveActualWeather(actualWeatherInfo));
    }
}
