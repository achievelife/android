package cse442.achievelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class jogActivity extends AppCompatActivity {
Button jog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jog);
       jog = (Button) findViewById(R.id.button29);
        jog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(jogActivity.this,jogdesActivity.class));
                // Perform action on click
            }
        });
    }
}