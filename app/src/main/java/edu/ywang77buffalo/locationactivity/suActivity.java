package edu.ywang77buffalo.locationactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class suActivity extends AppCompatActivity {
Button suA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su);
        suA = (Button) findViewById(R.id.button25);
        suA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(suActivity.this,clubActivity.class));
                // Perform action on click
            }
        });
    }
}