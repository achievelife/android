package cse442.achievelife;

/**
 * Created by jrser on 9/29/2016.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class activity_log extends AppCompatActivity {

    //TextView textView1 = (TextView)findViewById(R.id.textView1);
    TextView textView1;
    ListView listView1;

    String[] mobileArray = {"id:Sample0 name:fitness start:10:47 end:10:47","id:Sample1 name:intelligence start:10:47 end:10:47","id:Sample2 name:social start:10:47 end:10:47","id:Sample3 name:finance start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47","id:Sample4 name:fitness start:10:47 end:10:47"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_log);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setText("ACTIVITY LOG");

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);

        listView1 = (ListView) findViewById(R.id.mobile_list);
        listView1.setAdapter(adapter);
    }
}
