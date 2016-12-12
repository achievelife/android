package com.example.english.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class readingActivity extends AppCompatActivity {
    Button reading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        reading = (Button) findViewById(R.id.button23);
        reading.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(readingActivity.this,readingdesActivity.class));
                // Perform action on click
            }
        });
    }
}
