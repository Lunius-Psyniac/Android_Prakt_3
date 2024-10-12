package com.example.android_prakt_3;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatDelegate;

public class DropdownActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}