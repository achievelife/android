package edu.ywang77buffalo.locationactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class studyActivity extends AppCompatActivity {
Button study;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        study = (Button) findViewById(R.id.button20);
        study.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(studyActivity.this,studydesActivity.class));
                // Perform action on click
            }
        });
    }
}
