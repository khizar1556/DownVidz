package com.ui.downvid.settings;

import android.os.Bundle;

import com.ui.downvid.R;

public class VideoAudioSettingsFragment extends BasePreferenceFragment {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.video_audio_settings);
    }
}
