/*package cse442.achievelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button aa;
    Button cp;
    Button lw;
    Button st;
    Button su;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aa = (Button) findViewById(R.id.button2);
        cp = (Button) findViewById(R.id.button4);
        lw = (Button) findViewById(R.id.button7);
        su = (Button) findViewById(R.id.button12);
        st = (Button) findViewById(R.id.button13);
        aa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,aaActivity.class));
                // Perform action on click
            }
        });
       cp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,cpActivity.class));
                // Perform action on click
            }
        });
        lw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,lwActivity.class));
                // Perform action on click
            }
        });
        su.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,suActivity.class));
                // Perform action on click
            }
        });
        st.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,stActivity.class));
                // Perform action on click
            }
        });
    }
}
*/