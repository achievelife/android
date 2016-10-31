package cse442.achievelife;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Button intelligence_Button = (Button) findViewById(R.id.intelligenceButton);
        Button fitness_Button = (Button) findViewById(R.id.fitnessButton);
        Button activity_log_Button = (Button) findViewById(R.id.activity_logButton);
        Button leaderBoard_Button = (Button) findViewById(R.id.leaderBoardButton);
        Button settings_Button = (Button) findViewById(R.id.settingsButton);

        intelligence_Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), main_intelligence.class);
                startActivity(intent);
            }
        });

        fitness_Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), FitnessMap.class);
                startActivity(intent);
            }
        });

        activity_log_Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), activity_log.class);
                startActivity(intent);
            }
        });

    }
}
