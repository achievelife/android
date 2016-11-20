package cse442.achievelife;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clubActivity extends AppCompatActivity {
Button club;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        club = (Button) findViewById(R.id.button26);
        club.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(clubActivity.this,clubdesActivity.class));
                // Perform action on click
            }
        });
    }
}
