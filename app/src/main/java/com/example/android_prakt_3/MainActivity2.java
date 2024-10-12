package com.example.android_prakt_3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button go_back_button = findViewById(R.id.back);
        go_back_button.setOnClickListener(v -> finish());
        Button readButton = findViewById(R.id.read);
        readButton.setOnClickListener(v -> readData());
    }
    @SuppressLint("CheckResult")
    private void readData() {
        TextView outputTextView = findViewById(R.id.textView);
        DataStoreManager.getInstance(this).getDataStore()
                .data()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prefs -> {
                    String savedInput = prefs.get(DataStoreManager.TEXT_INPUT_KEY);
                    if (savedInput != null) {
                        outputTextView.setText(savedInput);
                    } else {
                        Toast.makeText(MainActivity2.this, "Nothing found", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
