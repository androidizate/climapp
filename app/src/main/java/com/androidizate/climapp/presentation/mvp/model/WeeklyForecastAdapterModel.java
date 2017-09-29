package com.androidizate.climapp.presentation.mvp.model;

import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.utils.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author Andres Oller.
 */

public class WeeklyForecastAdapterModel {

    private DateUtils utils;
    private Map<Integer, List<DailyWeather>> itemsMap = new HashMap<>();
    private List<DailyWeather> items = new ArrayList<>();

    @Inject
    WeeklyForecastAdapterModel(DateUtils utils) {
        this.utils = utils;
    }

    public void setItems(List<DailyWeather> items) {
        this.items = items;
        itemsToMap();
    }

    private void itemsToMap() {
        int position = -1;
        for (DailyWeather dailyWeather : items) {
            List<DailyWeather> dailyWeathers = new ArrayList<>();
            String date = utils.formatDate(dailyWeather.getDate());
            if (itemsMap.get(position) != null &&
                    date.equals(utils.formatDate(itemsMap.get(position).get(0).getDate()))) {
                dailyWeathers = itemsMap.get(position);
            } else {
                position++;
            }
            dailyWeathers.add(dailyWeather);
            itemsMap.put(position, dailyWeathers);
        }
    }

    public List<DailyWeather> getItemsForDate(int position) {
        return itemsMap.get(position);
    }

    public int getItemsCount() {
        return itemsMap.size();
    }
}
