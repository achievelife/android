package com.example.english.app;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class readingstartActivity extends AppCompatActivity {

    private boolean Paused = false;
    private boolean Canceled = false;
    private long tRemaining = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readingstart);
        final TextView View = (TextView) findViewById(R.id.message3);
        final Button Start = (Button) findViewById(R.id.start3);
        final Button Pause = (Button) findViewById(R.id.pause3);
        final Button Resume = (Button) findViewById(R.id.resume3);
        final Button Cancel = (Button) findViewById(R.id.cancel3);

        Pause.setEnabled(false);
        Resume.setEnabled(false);
        Cancel.setEnabled(false);

        Start.setOnClickListener(new android.view.View.OnClickListener() {
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


        Pause.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paused = true;
                Start.setEnabled(false);
                Pause.setEnabled(false);
                Resume.setEnabled(true);
                Cancel.setEnabled(true);
            }
        });


        Resume.setOnClickListener(new android.view.View.OnClickListener() {
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

                Cancel.setOnClickListener(new android.view.View.OnClickListener() {
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

        Cancel.setOnClickListener(new android.view.View.OnClickListener() {
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

