package edu.ywang77buffalo.locationactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gymdesActivity extends AppCompatActivity {
Button gymstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymdes);
        gymstart = (Button) findViewById(R.id.button18);
        gymstart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(gymdesActivity.this,gymstartActivity.class));
                // Perform action on click
            }
        });
    }
}
