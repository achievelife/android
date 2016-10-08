package edu.ywang77buffalo.achievelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntelligenceActivity extends AppCompatActivity {
Button study;
    Button read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligence);
        study = (Button) findViewById(R.id.button5);
        read = (Button) findViewById(R.id.button6);
       study.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(IntelligenceActivity.this,StudyingActivity.class));
                // Perform action on click
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(IntelligenceActivity.this,AttendenceActivity.class));
                // Perform action on click
            }
        });
    }
}
