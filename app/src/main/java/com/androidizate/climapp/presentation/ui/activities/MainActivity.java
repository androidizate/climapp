/* (c) Androidizate. Todos los derechos reservados. */
package com.androidizate.climapp.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.androidizate.climapp.R;
import com.androidizate.climapp.presentation.ClimappApplication;
import com.androidizate.climapp.presentation.constants.MapLayersConstants;
import com.androidizate.climapp.presentation.ui.fragments.ForecastMapFragment;
import com.androidizate.climapp.presentation.ui.fragments.TodayForecastFragment;
import com.androidizate.climapp.presentation.ui.fragments.WeeklyForecastFragment;

import static com.androidizate.climapp.presentation.constants.MapLayersConstants.LAYER_CLOUDS;
import static com.androidizate.climapp.presentation.constants.MapLayersConstants.LAYER_PRECIPITATION;
import static com.androidizate.climapp.presentation.constants.MapLayersConstants.LAYER_PRESSURE;
import static com.androidizate.climapp.presentation.constants.MapLayersConstants.LAYER_RAIN;
import static com.androidizate.climapp.presentation.constants.MapLayersConstants.LAYER_SNOW;
import static com.androidizate.climapp.presentation.constants.MapLayersConstants.LAYER_TEMPERATURE;
import static com.androidizate.climapp.presentation.constants.MapLayersConstants.LAYER_WIND;

/**
 * @author Andres Oller.
 **/
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    MenuItem layersItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ClimappApplication) getApplication()).getComponent().inject(this);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.navigation_daily);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_daily:
                loadFragment(new TodayForecastFragment());
                return true;
            case R.id.navigation_weekly:
                loadFragment(new WeeklyForecastFragment());
                return true;
            case R.id.navigation_map:
                loadFragment(new ForecastMapFragment());
                return true;
            default:
                loadFragment(new TodayForecastFragment());
                return true;
        }
    }

    private void loadFragment(Fragment fragment) {
        if (fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()) != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragmentManager.findFragmentByTag(fragment.getClass().getSimpleName()), fragment.getClass().getSimpleName())
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
        updateMenu(fragment);
    }

    private void updateMenu(Fragment fragment) {
        if (layersItem != null) {
            if (fragment instanceof ForecastMapFragment) {
                layersItem.setVisible(true);
            } else {
                layersItem.setVisible(false);
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        layersItem = menu.findItem(R.id.layers);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clouds:
                updateMapLayer(LAYER_CLOUDS);
                return true;
            case R.id.temperature:
                updateMapLayer(LAYER_TEMPERATURE);
                return true;
            case R.id.pressure:
                updateMapLayer(LAYER_PRESSURE);
                return true;
            case R.id.precipitation:
                updateMapLayer(LAYER_PRECIPITATION);
                return true;
            case R.id.wind:
                updateMapLayer(LAYER_WIND);
                return true;
            case R.id.snow:
                updateMapLayer(LAYER_SNOW);
                return true;
            case R.id.rain:
                updateMapLayer(LAYER_RAIN);
                return true;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.refresh:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateMapLayer(@MapLayersConstants String layer) {
        ((ForecastMapFragment) fragmentManager.findFragmentByTag(ForecastMapFragment.class.getSimpleName())).reloadTileProvider(layer);
    }
}
