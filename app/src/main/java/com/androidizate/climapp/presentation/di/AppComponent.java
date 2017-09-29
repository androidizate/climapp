package com.androidizate.climapp.presentation.di;

import com.androidizate.climapp.data.di.DataModule;
import com.androidizate.climapp.domain.di.DomainModule;
import com.androidizate.climapp.presentation.ui.activities.MainActivity;
import com.androidizate.climapp.presentation.ui.activities.SettingsActivity;
import com.androidizate.climapp.presentation.ui.fragments.ForecastMapFragment;
import com.androidizate.climapp.presentation.ui.fragments.SettingsFragment;
import com.androidizate.climapp.presentation.ui.fragments.TodayForecastFragment;
import com.androidizate.climapp.presentation.ui.fragments.WeeklyForecastFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Andres Oller
 */
@Singleton
@Component(modules = {AppModule.class,
        DataModule.class,
        DomainModule.class})
public interface AppComponent {
    void inject(MainActivity activity);

    void inject(SettingsActivity activity);

    void inject(ForecastMapFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(WeeklyForecastFragment fragment);

    void inject(TodayForecastFragment fragment);
}
