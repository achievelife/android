package edu.ywang77buffalo.achievelife;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button Int;
    Button Fit;
    Button Fin;
    Button Soc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Int = (Button) findViewById(R.id.button1);
        Fit = (Button) findViewById(R.id.button2);
        Fin = (Button) findViewById(R.id.button3);
        Soc = (Button) findViewById(R.id.button4);
        Int.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,IntelligenceActivity.class));
                // Perform action on click
            }
        });
        Fit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FitnessActivity.class));
                // Perform action on click
            }
        });
        Fin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FinanceActivity.class));
                // Perform action on click
            }
        });
        Soc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SocialActivity.class));
                // Perform action on click
            }
        });
    }
}
