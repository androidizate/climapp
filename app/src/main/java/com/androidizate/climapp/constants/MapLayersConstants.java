package com.androidizate.climapp.constants;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_CLOUDS;
import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_PRECIPITATION;
import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_PRESSURE;
import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_RAIN;
import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_SNOW;
import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_TEMPERATURE;
import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_WIND;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author Andres Oller.
 */
@Retention(SOURCE)
@StringDef({LAYER_CLOUDS, LAYER_TEMPERATURE, LAYER_PRESSURE, LAYER_PRECIPITATION, LAYER_WIND, LAYER_SNOW, LAYER_RAIN})
public @interface MapLayersConstants {
    public static final String LAYER_CLOUDS = "clouds";
    public static final String LAYER_TEMPERATURE = "temp";
    public static final String LAYER_PRESSURE = "pressure";
    public static final String LAYER_PRECIPITATION = "precipitation";
    public static final String LAYER_WIND = "wind";
    public static final String LAYER_SNOW = "snow";
    public static final String LAYER_RAIN = "rain";
}
