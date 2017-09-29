package com.androidizate.climapp.presentation.ui.fragments;

import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.androidizate.climapp.R;
import com.androidizate.climapp.presentation.ClimappApplication;

/**
 * @author Andres Oller.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ClimappApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_general);

        ListPreference updateFrequencyPreference = (ListPreference) findPreference(getString(R.string.key_update_frequency));
        updateFrequencyPreference.setSummary(String.format(getString(R.string.pref_summary_update_frequency), updateFrequencyPreference.getEntry()));
        updateFrequencyPreference.setOnPreferenceChangeListener(this);

        ListPreference measurementSystemPreference = (ListPreference) findPreference(getString(R.string.key_measurement_system));
        measurementSystemPreference.setSummary(String.format(getString(R.string.pref_summary_measurement_system), measurementSystemPreference
                .getEntry()));
        measurementSystemPreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        int i = ((ListPreference) preference).findIndexOfValue(newValue.toString());
        CharSequence[] entries = ((ListPreference) preference).getEntries();
        preference.setSummary(entries[i]);
        return true;
    }
}
