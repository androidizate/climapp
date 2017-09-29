package com.androidizate.climapp.data.db.repository.weekly;

import com.androidizate.climapp.data.db.model.DailyWeatherDao;
import com.androidizate.climapp.data.db.model.DaoSession;
import com.androidizate.climapp.data.db.model.DailyWeather;
import com.androidizate.climapp.data.db.model.Location;
import com.androidizate.climapp.data.network.model.City;
import com.androidizate.climapp.data.network.model.Clouds;
import com.androidizate.climapp.data.network.model.DayInfo;
import com.androidizate.climapp.data.network.model.Main;
import com.androidizate.climapp.data.network.model.Weather;
import com.androidizate.climapp.data.network.model.WeatherCondition;
import com.androidizate.climapp.data.network.model.WeatherInfo;
import com.androidizate.climapp.data.network.model.Wind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */

public class DailyWeatherRepositoryImpl implements DailyWeatherRepository {

    private DaoSession daoSession;

    @Inject
    public DailyWeatherRepositoryImpl(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Observable<Boolean> isDailyWeatherEmpty(final long cityId) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(daoSession.getDailyWeatherDao()
                        .queryBuilder()
                        .where(DailyWeatherDao.Properties.CityId.eq(cityId))
                        .buildCount().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveDailyWeather(final DailyWeather dailyWeather) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                daoSession.getDailyWeatherDao().insert(dailyWeather);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> saveDailyWeatherList(final List<DailyWeather> dailyWeatherList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                daoSession.getDailyWeatherDao().insertInTx(dailyWeatherList);
                return true;
            }
        });
    }

    @Override
    public Observable<Map<Long, List<DailyWeather>>> getDailyWeatherForCity(final long cityId) {
        Map<Long, List<DailyWeather>> map = new HashMap<>();
        return Observable.fromCallable(new Callable<Map<Long, List<DailyWeather>>>() {
            @Override
            public Map<Long, List<DailyWeather>> call() throws Exception {
                List<DailyWeather> dailyWeathers = daoSession.getDailyWeatherDao().getSession().queryBuilder(DailyWeather.class).where(DailyWeatherDao.Properties.CityId.eq(cityId)).build().list();
                map.put(cityId, dailyWeathers);
                return map;
            }
        });
    }

    @Override
    public Observable<Map<Long, List<DailyWeather>>> getDailyWeatherForAllCities() {
        return Observable.fromCallable(new Callable<Map<Long, List<DailyWeather>>>() {
            @Override
            public Map<Long, List<DailyWeather>> call() throws Exception {
                Map<Long, List<DailyWeather>> dailyWeatherMap = new HashMap<>();

                for (Location location : daoSession.getLocationDao().loadAll()) {
                    dailyWeatherMap.put(location.getId(), daoSession.getDailyWeatherDao().getSession().queryBuilder(DailyWeather.class).where(DailyWeatherDao.Properties.CityId.eq(location.getId())).build().list());
                }

                return dailyWeatherMap;
            }
        });
    }

    @Override
    public Map<Long, List<DailyWeather>> saveDailyWeather(WeatherInfo weatherInfo) {
        Map<Long, List<DailyWeather>> citiesDailyWeatherMap = new HashMap<>();

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

        citiesDailyWeatherMap.put(city.getId(), daoSession.getDailyWeatherDao().loadAll());
        return citiesDailyWeatherMap;
    }

    @Override
    public boolean dailyWeatherExists(long cityId) {
        return !(daoSession.getDailyWeatherDao()
                .queryBuilder()
                .where(DailyWeatherDao.Properties.CityId.eq(cityId))
                .buildCount().count() > 0);
    }
}
