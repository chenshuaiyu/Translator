package com.example.chen.translator.ui.fragment.settings;

import android.os.Bundle;

import com.example.chen.translator.R;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/4 14:11
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings_screen);

        findPreference("key_theme_color").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                return true;
            }
        });
    }
}
