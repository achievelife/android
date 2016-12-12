package com.example.english.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class jogdesActivity extends AppCompatActivity {
    Button jogstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogdes);
        jogstart = (Button) findViewById(R.id.button30);
        jogstart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(jogdesActivity.this,jogstartActivity.class));
                // Perform action on click
            }
        });
    }
}
