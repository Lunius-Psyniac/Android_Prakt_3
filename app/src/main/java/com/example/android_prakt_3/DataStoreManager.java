package com.example.android_prakt_3;

import android.content.Context;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava2.RxDataStore;

public class DataStoreManager {

    private static DataStoreManager instance;
    private final RxDataStore<Preferences> dataStore;
    public static final Preferences.Key<String> TEXT_INPUT_KEY = PreferencesKeys.stringKey("input");
    private DataStoreManager(Context context) {
        dataStore = new RxPreferenceDataStoreBuilder(context, "text_input").build();
    }
    public static synchronized DataStoreManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataStoreManager(context.getApplicationContext());
        }
        return instance;
    }
    public RxDataStore<Preferences> getDataStore() {
        return dataStore;
    }
}
