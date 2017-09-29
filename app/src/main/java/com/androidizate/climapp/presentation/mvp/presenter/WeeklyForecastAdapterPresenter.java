package com.androidizate.climapp.presentation.mvp.presenter;

import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.presentation.mvp.model.WeeklyForecastAdapterModel;
import com.androidizate.climapp.presentation.mvp.view.WeeklyForecastAdapterView;

import java.util.List;
import java.util.zip.ZipFile;

import javax.inject.Inject;

/**
 * @author Andres Oller
 */
public class WeeklyForecastAdapterPresenter {

    WeeklyForecastAdapterView view;
    WeeklyForecastAdapterModel model;

    @Inject
    WeeklyForecastAdapterPresenter(WeeklyForecastAdapterModel model) {
        this.model = model;
    }

    public void setView(WeeklyForecastAdapterView view) {
        this.view = view;
    }

    public void setItems(List<DailyWeather> items) {
        if (view != null) {
            model.setItems(items);
            view.refreshAdapter();
        }
    }

    public void bindViewHolder(int position) {
        if (view != null) {
            view.setHourlyItems(model.getItemsForDate(position));
        }
    }

    public int getItemsCount() {
        return model.getItemsCount();
    }
}
