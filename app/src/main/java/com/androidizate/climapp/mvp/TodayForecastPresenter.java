package com.androidizate.climapp.mvp;

import android.util.Log;

import com.androidizate.climapp.dao.DaoSession;
import com.androidizate.climapp.dto.WeatherInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Marcos Toranzo
 */

public class TodayForecastPresenter implements Callback<WeatherInfo> {

    private TodayForecastView view;
    private TodayForecastModel model;

    public TodayForecastPresenter(TodayForecastView todayForecastView, DaoSession daoSession) {
        this.view = todayForecastView;
        this.model = new TodayForecastModel(daoSession);
    }

    public void getTodayForecast() {
        model.getTodayForecast(view.getWeatherApiId(), view.getLanguage()).enqueue(this);
    }

    @Override
    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {

    }

    @Override
    public void onFailure(Call<WeatherInfo> call, Throwable t) {
        Log.d(this.getClass().getSimpleName(), "Error de conexion");
    }
}
