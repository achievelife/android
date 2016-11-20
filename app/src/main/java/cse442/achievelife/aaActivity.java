package cse442.achievelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class aaActivity extends AppCompatActivity {
    Button aaA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aa);
        aaA = (Button) findViewById(R.id.button14);
        aaA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(aaActivity.this,gymActivity.class));
                // Perform action on click
            }
        });
    }
}
