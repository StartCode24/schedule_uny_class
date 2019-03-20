package com.starcode.skedi.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.starcode.skedi.R;

public class PreferenceAlarmFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        updatePreferenceSummary();
    }

    public void updatePreferenceSummary(){
        SharedPreferences sharedPreferences=getPreferenceScreen().getSharedPreferences();

//        set nagging preference summary
        int nagMinutes = sharedPreferences.getInt("nagMinutes", getResources().getInteger(R.integer.default_nag_minutes));
        int nagSeconds = sharedPreferences.getInt("nagSeconds", getResources().getInteger(R.integer.default_nag_seconds));
        Preference nagPreference = findPreference("nagInterval");
        String nagMinutesText = String.format(getActivity().getResources().getQuantityString(R.plurals.time_minute, nagMinutes), nagMinutes);
        String nagSecondsText = String.format(getActivity().getResources().getQuantityString(R.plurals.time_second, nagSeconds), nagSeconds);
        nagPreference.setSummary(String.format("%s %s", nagMinutesText, nagSecondsText));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        updatePreferenceSummary();
    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }


}
