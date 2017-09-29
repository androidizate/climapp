package com.androidizate.climapp.data.db.repository.actual;

import com.androidizate.climapp.data.db.model.ActualWeather;
import com.androidizate.climapp.data.db.model.ActualWeatherDao;
import com.androidizate.climapp.data.db.model.DaoSession;
import com.androidizate.climapp.data.db.model.Location;
import com.androidizate.climapp.data.network.model.ActualWeatherInfo;
import com.androidizate.climapp.data.network.model.Clouds;
import com.androidizate.climapp.data.network.model.Coordinates;
import com.androidizate.climapp.data.network.model.Main;
import com.androidizate.climapp.data.network.model.Sys;
import com.androidizate.climapp.data.network.model.Weather;
import com.androidizate.climapp.data.network.model.WeatherCondition;
import com.androidizate.climapp.data.network.model.Wind;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */

public class ActualWeatherRepositoryImpl implements ActualWeatherRepository {

    private DaoSession daoSession;

    @Inject
    public ActualWeatherRepositoryImpl(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Observable<Boolean> isActualWeatherEmpty(final long cityId) {
        return Observable.fromCallable(() -> !(daoSession.getActualWeatherDao()
                .queryBuilder()
                .where(ActualWeatherDao.Properties.CityId.eq(cityId))
                .buildCount().count() > 0));
    }

    @Override
    public Map<Long, ActualWeather> saveActualWeather(ActualWeatherInfo actualWeatherInfo) {
        Map<Long, ActualWeather> citiesActualWeather = new HashMap<>();

        if (daoSession.getLocationDao().load(actualWeatherInfo.getId()) == null) {
            Location location = new Location();
            location.setId(actualWeatherInfo.getId());
            location.setCountry(actualWeatherInfo.getSys().getCountry());
            location.setName(actualWeatherInfo.getName());
            Coordinates coordinates = actualWeatherInfo.getCoordinates();
            if (coordinates != null) {
                location.setLatitude(coordinates.getLatitude());
                location.setLongitude(coordinates.getLongitude());
            }
            daoSession.getLocationDao().insertOrReplace(location);
        } else {
            daoSession.getActualWeatherDao()
                    .queryBuilder()
                    .where(ActualWeatherDao.Properties.CityId.eq(actualWeatherInfo.getId()))
                    .buildDelete()
                    .executeDeleteWithoutDetachingEntities();
        }

        ActualWeather actualWeather = new ActualWeather();

        actualWeather.setCityId(actualWeatherInfo.getId());
        actualWeather.setDate(actualWeatherInfo.getDate());
        actualWeather.setBase(actualWeatherInfo.getBase());
        actualWeather.setVisibility(actualWeatherInfo.getVisibility());

        WeatherCondition rain = actualWeatherInfo.getRain();
        if (rain != null) {
            actualWeather.setLastThreeHoursRain(rain.getLastThreeHours());
        }

        WeatherCondition snow = actualWeatherInfo.getSnow();
        if (snow != null) {
            actualWeather.setLastThreeHoursSnow(snow.getLastThreeHours());
        }

        Weather weather = actualWeatherInfo.getWeather();
        if (weather != null) {
            actualWeather.setWeatherId(weather.getId());
            actualWeather.setWeatherDescription(weather.getDescription());
            actualWeather.setWeatherMain(weather.getMain());
            actualWeather.setWeatherIcon(weather.getDescription());
        }

        Clouds clouds = actualWeatherInfo.getClouds();
        if (clouds != null) {
            actualWeather.setAll(clouds.getAll());
        }

        Main main = actualWeatherInfo.getMain();
        if (main != null) {
            actualWeather.setTemperature(main.getTemperature());
            actualWeather.setMaxTemperature(main.getMaxTemperature());
            actualWeather.setMinimumTemperature(main.getMinimumTemperature());
            actualWeather.setGroundLevel(main.getGroundLevel());
            actualWeather.setHumidity(main.getHumidity());
            actualWeather.setPressure(main.getPressure());
            actualWeather.setSeaLevel(main.getSeaLevel());
        }

        Wind wind = actualWeatherInfo.getWind();
        if (wind != null) {
            actualWeather.setSpeed(wind.getSpeed());
            actualWeather.setDegrees(wind.getDegrees());
        }

        Sys sys = actualWeatherInfo.getSys();
        if (sys != null) {
            actualWeather.setType(sys.getType());
            actualWeather.setSysId(sys.getId());
            actualWeather.setSysMessage(sys.getMessage());
            actualWeather.setSunrise(sys.getSunrise());
            actualWeather.setSunset(sys.getSunset());
        }

        daoSession.getActualWeatherDao().insert(actualWeather);

        citiesActualWeather.put(actualWeatherInfo.getId(), daoSession.getActualWeatherDao().load(actualWeatherInfo.getId()));
        return citiesActualWeather;
    }

    @Override
    public Observable<Map<Long, ActualWeather>> getActualWeather(final long cityId) {
        Map<Long, ActualWeather> map = new HashMap<>();
        return Observable.fromCallable(() -> {
            map.put(cityId, daoSession.getActualWeatherDao().load(cityId));
            return map;
        });
    }
}
