package com.androidizate.climapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidizate.climapp.ClimappApplication;
import com.androidizate.climapp.R;
import com.androidizate.climapp.mvp.TodayForecastPresenter;
import com.androidizate.climapp.mvp.TodayForecastView;

import java.util.Locale;

/**
 * @author Andres Oller.
 */
public class TodayForecastFragment extends Fragment implements TodayForecastView {

    TodayForecastPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new TodayForecastPresenter(this, ((ClimappApplication) getActivity().getApplication()).getDaoSession());
        return inflater.inflate(R.layout.fragment_today_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getTodayForecast();
    }

    @Override
    public String getWeatherApiId() {
        return getString(R.string.weather_api_id);
    }

    @Override
    public String getLanguage() {
        return Locale.getDefault().getLanguage();
    }
}
