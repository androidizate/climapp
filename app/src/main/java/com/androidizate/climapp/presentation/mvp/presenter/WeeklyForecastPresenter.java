package com.androidizate.climapp.presentation.mvp.presenter;

import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.domain.interactors.weekly.WeeklyForecastInteractor;
import com.androidizate.climapp.presentation.mvp.view.WeeklyForecastView;
import com.androidizate.climapp.presentation.ui.fragments.WeeklyForecastFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Andres Oller
 */

public class WeeklyForecastPresenter implements Observer {

    private WeeklyForecastView view;
    private WeeklyForecastInteractor interactor;

    @Inject
    public WeeklyForecastPresenter(WeeklyForecastInteractor interactor) {
        this.interactor = interactor;
    }

    public void setView(WeeklyForecastFragment view) {
        this.view = view;
    }

    public void loadData() {
        if (view != null) {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("q", "Cordoba, ar");
            parameters.put("appid", view.getWeatherApiKey());
            parameters.put("lang", view.getLocale());

            interactor.getDailyWeatherForCity(3860259, parameters).subscribe(this);
        }
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull Object o) {
        if (view != null) {
            view.setAdapterData(((Map<Long, List<DailyWeather>>) o).get(Long.valueOf(3860259)));
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
