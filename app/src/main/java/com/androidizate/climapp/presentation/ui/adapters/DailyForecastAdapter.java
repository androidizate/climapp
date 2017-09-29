package com.androidizate.climapp.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidizate.climapp.R;
import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.presentation.mvp.presenter.WeeklyForecastAdapterPresenter;
import com.androidizate.climapp.presentation.mvp.view.WeeklyForecastAdapterView;

import java.util.List;

import javax.inject.Inject;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * @author Andres Oller
 */

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.ViewHolder> implements WeeklyForecastAdapterView {

    private Context context;
    private HourlyForecastAdapter adapter;
    private WeeklyForecastAdapterPresenter presenter;

    @Inject
    public DailyForecastAdapter(Context context, HourlyForecastAdapter adapter, WeeklyForecastAdapterPresenter presenter) {
        this.context = context;
        this.adapter = adapter;
        this.presenter = presenter;
        presenter.setView(this);
    }

    public void setItems(List<DailyWeather> items) {
        presenter.setItems(items);
        notifyDataSetChanged();
    }

    @Override
    public DailyForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_forecast, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        presenter.bindViewHolder(position);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemsCount();
    }

    @Override
    public void setHourlyItems(List<DailyWeather> itemsForDate) {
        adapter.setItems(itemsForDate);
    }

    @Override
    public void refreshAdapter() {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.hourly_forecast_recycler_view);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
