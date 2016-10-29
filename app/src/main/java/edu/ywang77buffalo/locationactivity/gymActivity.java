package edu.ywang77buffalo.locationactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gymActivity extends AppCompatActivity {
Button gym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);
        gym = (Button) findViewById(R.id.button15);
        gym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(gymActivity.this,gymdesActivity.class));
                // Perform action on click
            }
        });
    }
}
