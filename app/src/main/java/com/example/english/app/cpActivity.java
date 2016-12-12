package com.example.english.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cpActivity extends AppCompatActivity {
    Button cpA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp);
        cpA = (Button) findViewById(R.id.button19);
        cpA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(cpActivity.this,studyActivity.class));
                // Perform action on click
            }
        });
    }
}

