package edu.ywang77buffalo.locationactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class readingdesActivity extends AppCompatActivity {
Button readingstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readingdes);
       readingstart = (Button) findViewById(R.id.button24);
        readingstart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(readingdesActivity.this,readingstartActivity.class));
                // Perform action on click
            }
        });
    }
}