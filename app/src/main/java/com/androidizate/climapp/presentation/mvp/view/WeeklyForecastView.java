package com.androidizate.climapp.presentation.mvp.view;

import com.androidizate.climapp.data.db.model.ActualWeather;
import com.androidizate.climapp.data.db.model.DailyWeather;

import java.util.List;

/**
 * @author Andres Oller
 */

public interface WeeklyForecastView {
    String getWeatherApiKey();

    String getLocale();

    void setAdapterData(List<DailyWeather> actualWeathers);
}
