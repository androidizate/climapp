package com.androidizate.climapp.domain.interactors.actual;

import com.androidizate.climapp.data.db.model.ActualWeather;
import com.androidizate.climapp.data.db.repository.actual.ActualWeatherRepository;
import com.androidizate.climapp.data.network.NetworkRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */
public class ActualWeatherInteractorImpl implements ActualWeatherInteractor {

    NetworkRepository networkRepository;
    ActualWeatherRepository actualWeatherRepository;

    @Inject
    ActualWeatherInteractorImpl(NetworkRepository networkRepository, ActualWeatherRepository actualWeatherRepository) {
        this.networkRepository = networkRepository;
        this.actualWeatherRepository = actualWeatherRepository;
    }

    @Override
    public Observable<Boolean> isActualWeatherEmpty(long cityId) {
        return null;
    }

    @Override
    public Observable<Boolean> saveActualWeather(ActualWeather actualWeather) {
        return null;
    }

    @Override
    public Observable<Map<Long, ActualWeather>> getActualWeather(long cityId, Map<String, String> options) {
        return networkRepository.getActualWeather(options)
                .startWith(actualWeatherRepository.getActualWeather(cityId));
    }
}
