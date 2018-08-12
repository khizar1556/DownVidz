package com.ui.downvid.settings;

import android.os.Bundle;

import com.ui.downvid.R;

public class DebugSettingsFragment extends BasePreferenceFragment {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.debug_settings);
    }
}
