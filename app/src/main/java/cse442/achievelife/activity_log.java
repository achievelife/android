package cse442.achievelife;

/**
 * Created by jrser on 9/29/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class activity_log extends AppCompatActivity {


public class activity_log extends AppCompatActivity {

    //TextView textView1 = (TextView)findViewById(R.id.textView1);
    TextView textView1;
    ListView listView1;

    String[] mobileArray = {"id:Sample0 name:fitness start:10:47 end:10:47","id:Sample1 name:intelligence start:10:47 end:10:47","id:Sample2 name:social start:10:47 end:10:47","id:Sample3 name:finance start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_log);
    }
}
