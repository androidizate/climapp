package com.androidizate.climapp.presentation.di;

import android.app.Application;
import android.content.Context;

import com.androidizate.climapp.presentation.ClimappApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Andres Oller
 */
@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application.getApplicationContext();
    }
}
