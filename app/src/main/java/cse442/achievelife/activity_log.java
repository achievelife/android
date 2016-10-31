package cse442.achievelife;

/**
 * Created by jrser on 9/29/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class activity_log extends AppCompatActivity {

    //TextView textView1 = (TextView)findViewById(R.id.textView1);
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_log);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setText("Activity Log");
    }
}
