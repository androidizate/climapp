package com.androidizate.climapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidizate.climapp.R;
import com.androidizate.climapp.api.RestApiClient;
import com.androidizate.climapp.dao.WeatherInfo;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Andres Oller.
 */
public class TodayForecastFragment extends Fragment implements Callback<WeatherInfo> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RestApiClient restApiClient = new RestApiClient();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("q", "Cordoba,ar");
        parameters.put("appid", getString(R.string.weather_api_id));
        parameters.put("lang", Locale.getDefault().getLanguage());
        restApiClient.getWeatherInfo(parameters).enqueue(this);
    }

    @Override
    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
        Log.d(this.getClass().getSimpleName(), response.toString());
    }

    @Override
    public void onFailure(Call<WeatherInfo> call, Throwable t) {
        Log.d(this.getClass().getSimpleName(), "Error de conexion");
    }
}
