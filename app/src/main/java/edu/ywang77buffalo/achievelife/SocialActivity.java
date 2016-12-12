package edu.ywang77buffalo.achievelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SocialActivity extends AppCompatActivity {
Button party;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
       party = (Button) findViewById(R.id.button13);
        party.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SocialActivity.this,PartyActivity.class));
                // Perform action on click
            }
        });
    }
}
