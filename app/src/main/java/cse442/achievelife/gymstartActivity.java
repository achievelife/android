package cse442.achievelife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;

public class gymstartActivity extends AppCompatActivity {

    private boolean Paused = false;
    private boolean Canceled = false;
    private long tRemaining = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymstart);
        final TextView View = (TextView) findViewById(R.id.message);
        final Button Start = (Button) findViewById(R.id.start1);
        final Button Pause = (Button) findViewById(R.id.pause1);
        final Button Resume = (Button) findViewById(R.id.resume1);
        final Button Cancel = (Button) findViewById(R.id.cancel1);

        Pause.setEnabled(false);
        Resume.setEnabled(false);
        Cancel.setEnabled(false);

        Start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Paused = false;
                Canceled = false;
                Start.setEnabled(false);
                Pause.setEnabled(true);
                Resume.setEnabled(false);
                Cancel.setEnabled(true);

                long millisInFuture = 3600000;
                long countDownInterval = 1000;



               new CountDownTimer(millisInFuture, countDownInterval) {
                    public void onTick(long millisUntilFinished) {

                        if (Paused || Canceled) {

                            cancel();
                        } else {

                            View.setText("" + millisUntilFinished / 1000);

                            tRemaining = millisUntilFinished;
                        }
                    }

                    public void onFinish() {

                        View.setText("Done");
                        Start.setEnabled(true);
                        Pause.setEnabled(false);
                        Resume.setEnabled(false);
                        Cancel.setEnabled(false);
                    }
                }.start();
            }
        });


        Pause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Paused = true;
                Start.setEnabled(false);
                Pause.setEnabled(false);
                Resume.setEnabled(true);
                Cancel.setEnabled(true);
            }
        });


        Resume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Start.setEnabled(false);
                Pause.setEnabled(true);
                Resume.setEnabled(false);
                Cancel.setEnabled(true);


                Paused = false;
                Canceled = false;


                long millisInFuture = tRemaining;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture, countDownInterval) {
                    public void onTick(long millisUntilFinished) {
                        if (Paused || Canceled) {
                            cancel();
                        } else {
                            View.setText("" + millisUntilFinished / 1000);
                            tRemaining = millisUntilFinished;
                        }
                    }

                    public void onFinish() {

                        View.setText("Done");
                        Start.setEnabled(true);
                        Pause.setEnabled(false);
                        Resume.setEnabled(false);
                        Cancel.setEnabled(false);

                    }
                }.start();

                Cancel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Canceled = true;
                        Start.setEnabled(true);
                        Pause.setEnabled(false);
                        Resume.setEnabled(false);
                        Cancel.setEnabled(false);

                        View.setText("Activity has ended.");
                    }
                });
            }
        });

        Cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Canceled = true;
                Start.setEnabled(true);
                Pause.setEnabled(false);
                Resume.setEnabled(false);
                Cancel.setEnabled(false);

                View.setText("Activity has been cancelled.");
            }
        });

    }
}


