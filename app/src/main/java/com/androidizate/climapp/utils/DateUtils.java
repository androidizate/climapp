package com.androidizate.climapp.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

/**
 * @author Andres Oller.
 */
public class DateUtils {

    @Inject
    DateUtils() {

    }

    public String formatDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        return formatter.format(new Date(milliSeconds * 1000L));
    }

    public String getFormattedTime(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:ss", Locale.getDefault());
        return formatter.format(new Date(milliSeconds * 1000L));
    }
}
