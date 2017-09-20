package com.androidizate.climapp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.androidizate.climapp.R;
import com.androidizate.climapp.ui.fragments.SettingsFragment;

/**
 *  @author Andres Oller.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).commit();
    }
}
