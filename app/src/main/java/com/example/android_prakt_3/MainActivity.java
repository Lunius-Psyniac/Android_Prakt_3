package com.example.android_prakt_3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.datastore.preferences.core.MutablePreferences;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputField = findViewById(R.id.input);

        Button go_to_button = findViewById(R.id.go_to_2nd);
        go_to_button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity2.class)));
        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(v -> saveData());

        Spinner dropdown = findViewById(R.id.dropdown);
        dropdown.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.light_themes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        loadData();
    }
    @SuppressLint("CheckResult")
    private void saveData() {
        String inputText = inputField.getText().toString();

        DataStoreManager.getInstance(this).getDataStore()
                .updateDataAsync(prefsIn -> {
                    MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
                    mutablePreferences.set(DataStoreManager.TEXT_INPUT_KEY, inputText);
                    return Single.just(mutablePreferences);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    @SuppressLint("CheckResult")
    private void loadData() {
        DataStoreManager.getInstance(this).getDataStore()
                .data()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prefs -> {
                    String savedInput = prefs.get(DataStoreManager.TEXT_INPUT_KEY);
                    if (savedInput != null) {
                        inputField.setText(savedInput);
                    }
                });
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        String[] itemsArray = getResources().getStringArray(R.array.light_themes);
        String selectedItem = parent.getItemAtPosition(pos).toString();
        if (selectedItem.equals(itemsArray[0])) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        } else if (selectedItem.equals(itemsArray[1])) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (selectedItem.equals(itemsArray[2])) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
