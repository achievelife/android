package cse442.achievelife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by jrser on 11/20/2016.
 */

public class activity_description extends AppCompatActivity {

    //TextView textView1 = (TextView)findViewById(R.id.textView1);
    TextView textView1;
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_description);
    }
}