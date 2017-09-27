package com.androidizate.climapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidizate.climapp.ClimappApplication;
import com.androidizate.climapp.R;
import com.androidizate.climapp.api.RestApiClient;
import com.androidizate.climapp.dao.DailyWeather;
import com.androidizate.climapp.dao.DailyWeatherDao;
import com.androidizate.climapp.dao.DaoSession;
import com.androidizate.climapp.dao.Location;
import com.androidizate.climapp.dto.City;
import com.androidizate.climapp.dto.Clouds;
import com.androidizate.climapp.dto.DayInfo;
import com.androidizate.climapp.dto.Main;
import com.androidizate.climapp.dto.Weather;
import com.androidizate.climapp.dto.WeatherCondition;
import com.androidizate.climapp.dto.WeatherInfo;
import com.androidizate.climapp.dto.Wind;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Andres Oller.
 */
public class TodayForecastFragment extends Fragment implements Callback<WeatherInfo> {

    DaoSession daoSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RestApiClient restApiClient = new RestApiClient();
        Map<String, String> parameters = new HashMap<>();
        daoSession = ((ClimappApplication) getActivity().getApplication()).getDaoSession();
        //TODO agregar la llamada por latitud y longitud cuando este la geolocalización incluida.
        parameters.put("q", "Cordoba,ar");
        parameters.put("appid", getString(R.string.weather_api_id));
        parameters.put("lang", Locale.getDefault().getLanguage());
        //FIXME modificar esta llamada por la del clima actual.
        restApiClient.getWeeklyForecast(parameters).enqueue(this);
    }

    @Override
    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
        Log.d(this.getClass().getSimpleName(), response.toString());

        WeatherInfo weatherInfo = response.body();

        City city = weatherInfo.getCity();

        if (daoSession.getLocationDao().load(city.getId()) == null) {
            Location location = new Location();
            location.setId(city.getId());
            location.setCountry(city.getCountry());
            location.setName(city.getName());
            location.setLatitude(city.getCoordinates().getLatitude());
            location.setLongitude(city.getCoordinates().getLongitude());
            daoSession.getLocationDao().insertOrReplace(location);
        } else {
            daoSession.getDailyWeatherDao()
                    .queryBuilder()
                    .where(DailyWeatherDao.Properties.CityId.eq(city.getId()))
                    .buildDelete()
                    .executeDeleteWithoutDetachingEntities();
        }

        List<DayInfo> dayInfoList = weatherInfo.getDayInfo();

        for (DayInfo dayInfo : dayInfoList) {

            List<Weather> weatherList = dayInfo.getWeather();

            for (Weather weather : weatherList) {
                DailyWeather dailyWeather = new DailyWeather();

                dailyWeather.setCityId(city.getId());
                dailyWeather.setDate(dayInfo.getDate());

                WeatherCondition rain = dayInfo.getRain();
                if (rain != null) {
                    dailyWeather.setLastThreeHoursRain(rain.getLastThreeHours());
                }

                WeatherCondition snow = dayInfo.getSnow();
                if (snow != null) {
                    dailyWeather.setLastThreeHoursSnow(snow.getLastThreeHours());
                }

                Clouds clouds = dayInfo.getClouds();
                if (clouds != null) {
                    dailyWeather.setAll(clouds.getAll());
                }

                Main main = dayInfo.getMain();
                if (main != null) {
                    dailyWeather.setTemperature(main.getTemperature());
                    dailyWeather.setMaxTemperature(main.getMaxTemperature());
                    dailyWeather.setMinimumTemperature(main.getMinimumTemperature());
                    dailyWeather.setGroundLevel(main.getGroundLevel());
                    dailyWeather.setHumidity(main.getHumidity());
                    dailyWeather.setPressure(main.getPressure());
                    dailyWeather.setSeaLevel(main.getSeaLevel());
                    dailyWeather.setTempKf(main.getTempKf());
                }

                Wind wind = dayInfo.getWind();
                if (wind != null) {
                    dailyWeather.setSpeed(wind.getSpeed());
                    dailyWeather.setDegrees(wind.getDegrees());
                }

                dailyWeather.setWeatherId(weather.getId());
                dailyWeather.setWeatherIcon(weather.getIcon());
                dailyWeather.setWeatherDescription(weather.getDescription());
                dailyWeather.setWeatherMain(weather.getMain());

                daoSession.getDailyWeatherDao().insert(dailyWeather);
            }
        }
    }

    @Override
    public void onFailure(Call<WeatherInfo> call, Throwable t) {
        Log.d(this.getClass().getSimpleName(), "Error de conexion");
    }
}
