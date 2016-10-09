package cse442.achievelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinanceActivity extends AppCompatActivity {
    Button scholar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        scholar = (Button) findViewById(R.id.button11);
        scholar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FinanceActivity.this,ScholarActivity.class));
                // Perform action on click
            }
        });
    }
}

