package com.example.chittootechapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class QuestionActivity extends AppCompatActivity {

    TextView timer, submit, enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        timer = findViewById(R.id.timer);
        submit = findViewById(R.id.submit);
        enter = findViewById(R.id.enter);

        submit.setVisibility(View.INVISIBLE);

        long time = TimeUnit.SECONDS.toMillis(31);
        new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d"
                ,TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                ,TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                ,TimeUnit.MILLISECONDS.toSeconds(1)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));

                timer.setText(sDuration);
            }

            @Override
            public void onFinish() {
                enter.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
                timer.setVisibility(View.INVISIBLE);
            }
        }.start();
    }
}