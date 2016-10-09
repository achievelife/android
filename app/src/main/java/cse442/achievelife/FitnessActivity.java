package cse442.achievelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FitnessActivity extends AppCompatActivity {
    Button gym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        gym = (Button) findViewById(R.id.button9);
        gym.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FitnessActivity.this,GymActivity.class));
                // Perform action on click
            }
        });
    }
}
