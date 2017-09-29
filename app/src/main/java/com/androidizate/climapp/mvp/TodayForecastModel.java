package com.androidizate.climapp.mvp;

import com.androidizate.climapp.api.RestApiClient;
import com.androidizate.climapp.dao.DaoSession;
import com.androidizate.climapp.dto.WeatherInfo;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * @author Marcos Toranzo
 */

public class TodayForecastModel {

    DaoSession daoSession;

    public TodayForecastModel(DaoSession daoSession) {
        this.daoSession = daoSession;
    }


    public Call<WeatherInfo> getTodayForecast(String apiId, String language) {
        RestApiClient restApiClient = new RestApiClient();
        Map<String, String> parameters = new HashMap<>();
        //TODO agregar la llamada por latitud y longitud cuando este la geolocalización incluida.
        parameters.put("q", "Cordoba,ar");
        parameters.put("appid", apiId);
        parameters.put("lang", language);
        //FIXME modificar esta llamada por la del clima actual.
        return restApiClient.getActualWeather(parameters);
    }
}
