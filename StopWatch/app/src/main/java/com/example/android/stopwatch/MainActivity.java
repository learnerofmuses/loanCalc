package com.example.android.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timer;
    private Handler handler;
    private Update update;
    private long startTime; //need to record time when it is launched



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.timer);
        handler = new Handler();
        update = new Update();
        startTime = System.currentTimeMillis();
        handler.postDelayed(update, 1000);//schedules the update event for MS

    }

    public void buttonPressed(View v){
        handler.removeCallbacks(update);
    }


    private class Update implements Runnable{// tells it what to do when the event occurs

        public void run(){//occurs every second; displays in textview
            long current = System.currentTimeMillis(); // gives current time in MS
            long elapsed = (current - startTime)/1000;//number of seconds that have been elapsed since start time
            timer.setText(""+elapsed); //display the elapsed view in text
            handler.postDelayed(update, 1000);

        }
    }
}
