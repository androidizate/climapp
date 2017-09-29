package com.androidizate.climapp.domain.di;

import com.androidizate.climapp.domain.interactors.weekly.WeeklyForecastInteractor;
import com.androidizate.climapp.domain.interactors.weekly.WeeklyForecastInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Andres Oller
 */
@Module
public class DomainModule {

    @Provides
    WeeklyForecastInteractor provideActualWeatherInteractor(WeeklyForecastInteractorImpl weeklyForecastInteractor) {
        return weeklyForecastInteractor;
    }
}
