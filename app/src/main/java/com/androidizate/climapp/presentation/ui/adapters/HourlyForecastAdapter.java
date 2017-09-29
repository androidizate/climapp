package com.androidizate.climapp.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidizate.climapp.R;
import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.utils.DateUtils;
import com.androidizate.climapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Andres Oller
 */

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {

    Context context;
    DateUtils dateUtils;
    StringUtils stringUtils;
    private List<DailyWeather> items = new ArrayList<>();

    @Inject
    public HourlyForecastAdapter(Context context, DateUtils dateUtils, StringUtils stringUtils) {
        this.context = context;
        this.dateUtils = dateUtils;
        this.stringUtils = stringUtils;
    }

    public void setItems(List<DailyWeather> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public HourlyForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hour_forecast, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.hour.setText(dateUtils.getFormattedTime(items.get(position).getDate()));
        holder.temperature.setText(stringUtils.getFormattedMinMaxTemp(items.get(position).getMinimumTemperature(),
                items.get(position).getMaxTemperature()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hour;
        TextView temperature;
        ImageView weatherCondition;

        public ViewHolder(View itemView) {
            super(itemView);
            hour = itemView.findViewById(R.id.tv_hour);
            temperature = itemView.findViewById(R.id.tv_temperature_min_max);
            weatherCondition = itemView.findViewById(R.id.iv_weather_condition);
        }
    }
}
