package edu.ywang77buffalo.locationactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clubdesActivity extends AppCompatActivity {
Button clubstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubdes);
        clubstart = (Button) findViewById(R.id.button27);
        clubstart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(clubdesActivity.this,clubstartActivity.class));
                // Perform action on click
            }
        });
    }
}
