package com.example.english.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class stActivity extends AppCompatActivity {
    Button stA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st);
        stA = (Button) findViewById(R.id.button28);
        stA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(stActivity.this,jogActivity.class));
                // Perform action on click
            }
        });
    }
}
