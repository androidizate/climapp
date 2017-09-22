package com.androidizate.climapp.utils;

import android.content.Context;

import com.androidizate.climapp.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Andres Oller.
 */
public class SharedPreferencesManager {

    private Context context;

    public SharedPreferencesManager(Context context) {
        this.context = context;
    }

    public int getUpdateFrequency() {
        return context.getSharedPreferences(context.getString(R.string.user_preferences), MODE_PRIVATE)
                      .getInt(context.getString(R.string.key_update_frequency), 1);
    }

    public void setUpdateFrequency(int updateFrequency) {
        context.getSharedPreferences(context.getString(R.string.user_preferences), MODE_PRIVATE)
               .edit().putInt(context.getString(R.string.key_update_frequency), updateFrequency)
               .apply();
    }

    public int getMeasurementSystem() {
        return context.getSharedPreferences(context.getString(R.string.user_preferences), MODE_PRIVATE)
                      .getInt(context.getString(R.string.key_measurement_system), 1);
    }

    public void setMeasurementSystem(int measurementSystem) {
        context.getSharedPreferences(context.getString(R.string.user_preferences), MODE_PRIVATE)
               .edit().putInt(context.getString(R.string.key_measurement_system), measurementSystem)
               .apply();
    }

    public String getUpdateFrequencyValue() {
        return context.getSharedPreferences(context.getString(R.string.user_preferences), MODE_PRIVATE)
                      .getString(context.getString(R.string.key_measurement_system), "");
    }

    public String getMeasurementSystemValue() {
        return null;
    }
}
