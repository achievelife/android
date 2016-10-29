package edu.ywang77buffalo.locationactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class lwActivity extends AppCompatActivity {
    Button lwA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lw);
       lwA = (Button) findViewById(R.id.button22);
        lwA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(lwActivity.this,readingActivity.class));
                // Perform action on click
            }
        });
    }
}
