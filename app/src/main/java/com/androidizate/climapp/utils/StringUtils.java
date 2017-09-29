package com.androidizate.climapp.utils;

import android.content.Context;

import com.androidizate.climapp.R;

import javax.inject.Inject;

/**
 * @author Andres Oller.
 */
public class StringUtils {

    Context context;
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    StringUtils(Context context, SharedPreferencesManager sharedPreferencesManager) {
        this.context = context;
        this.sharedPreferencesManager = sharedPreferencesManager;
    }

    public String getFormattedMinMaxTemp(double minTemp, double maxTemp) {
        return context.getString(R.string.hourly_min_max, getFormattedTemp(minTemp), getFormattedTemp(maxTemp));
    }

    private String getFormattedTemp(double temp) {
        if (sharedPreferencesManager.getMeasurementSystem() == 1) {
            return String.valueOf(getTempFormattedCelsius(temp)) + "°C";
        } else {
            return String.valueOf(getTempFormattedFahrenheit(temp)) + "°F";
        }
    }

    public double getTempFormattedCelsius(double kelvinTemp) {
        return kelvinTemp - 273.16;
    }

    public double getTempFormattedFahrenheit(double kelvinTemp) {
        return  (((kelvinTemp - 273) * 9/5) + 32);
    }
}
