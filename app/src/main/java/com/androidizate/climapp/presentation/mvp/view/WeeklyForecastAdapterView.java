package com.androidizate.climapp.presentation.mvp.view;

import com.androidizate.climapp.data.db.model.DailyWeather;

import java.util.List;

/**
 * @author Andres Oller.
 */
public interface WeeklyForecastAdapterView {

    void setHourlyItems(List<DailyWeather> itemsForDate);

    void refreshAdapter();
}
