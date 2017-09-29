package com.androidizate.climapp.presentation;

import android.app.Application;

import com.androidizate.climapp.data.di.DataModule;
import com.androidizate.climapp.domain.di.DomainModule;
import com.androidizate.climapp.presentation.di.AppComponent;
import com.androidizate.climapp.presentation.di.AppModule;
import com.androidizate.climapp.presentation.di.DaggerAppComponent;

/**
 * @author Andres Oller
 */

public class ClimappApplication extends Application {

    private AppComponent component;
    private static final String BASE_URL = "http://api.openweathermap.org/";

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule(BASE_URL))
                .domainModule(new DomainModule())
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
