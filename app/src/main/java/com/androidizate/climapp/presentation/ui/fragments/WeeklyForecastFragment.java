package com.androidizate.climapp.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidizate.climapp.R;
import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.presentation.ClimappApplication;
import com.androidizate.climapp.presentation.mvp.presenter.WeeklyForecastPresenter;
import com.androidizate.climapp.presentation.mvp.view.WeeklyForecastView;
import com.androidizate.climapp.presentation.ui.adapters.DailyForecastAdapter;

import java.util.List;

import javax.inject.Inject;

import static java.util.Locale.getDefault;

/**
 * @author Andres Oller.
 */
public class WeeklyForecastFragment extends Fragment implements WeeklyForecastView {

    @Inject
    WeeklyForecastPresenter presenter;

    @Inject
    DailyForecastAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ClimappApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.setView(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekly_forecast, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.weekly_forecast_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public String getWeatherApiKey() {
        return getString(R.string.weather_api_id);
    }

    @Override
    public String getLocale() {
        return getDefault().getLanguage();
    }

    @Override
    public void setAdapterData(List<DailyWeather> dailyWeathers) {
        adapter.setItems(dailyWeathers);
    }
}
