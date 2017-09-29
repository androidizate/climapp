package com.androidizate.climapp.data.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.androidizate.climapp.data.db.model.DaoMaster;
import com.androidizate.climapp.data.db.model.DaoSession;
import com.androidizate.climapp.data.db.repository.actual.ActualWeatherRepository;
import com.androidizate.climapp.data.db.repository.actual.ActualWeatherRepositoryImpl;
import com.androidizate.climapp.data.db.repository.location.LocationRepository;
import com.androidizate.climapp.data.db.repository.location.LocationRepositoryImpl;
import com.androidizate.climapp.data.db.repository.weekly.DailyWeatherRepository;
import com.androidizate.climapp.data.db.repository.weekly.DailyWeatherRepositoryImpl;
import com.androidizate.climapp.data.network.NetworkRepository;
import com.androidizate.climapp.data.network.api.RestApiClient;
import com.androidizate.climapp.data.network.api.WeatherApiClient;
import com.androidizate.climapp.presentation.ClimappApplication;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Andres Oller
 */
@Module
public class DataModule {

    String baseUrl;

    public DataModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJavaAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverter, RxJava2CallAdapterFactory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverter)
                .addCallAdapterFactory(adapterFactory)
                .build();
    }

    @Provides
    @Singleton
    WeatherApiClient provideWeatherApiClient(Retrofit retrofit) {
        return retrofit.create(WeatherApiClient.class);
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(DaoMaster daoMaster) {
        return daoMaster.newSession();
    }

    @Provides
    @Singleton
    DaoMaster provideDaoMaster(Application application) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application, "climapp");
        Database db = helper.getWritableDb();
        return new DaoMaster(db);
    }

    @Provides
    @Singleton
    NetworkRepository provideNetworkRepository(RestApiClient repository) {
        return repository;
    }

    @Provides
    @Singleton
    DailyWeatherRepository provideDatabaseRepository(DailyWeatherRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    ActualWeatherRepository provideActualWeatherRepository(ActualWeatherRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    LocationRepository provideLocationRepository(LocationRepositoryImpl repository) {
        return repository;
    }
}
