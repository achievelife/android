package com.example.english.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class studydesActivity extends AppCompatActivity {
    Button studystart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studydes);
        studystart = (Button) findViewById(R.id.button21);
        studystart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(studydesActivity.this,studystartActivity.class));
                // Perform action on click
            }
        });
    }
}