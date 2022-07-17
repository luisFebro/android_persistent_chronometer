package com.example.chronometerpersistent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    // MAIN VARS
    Chronometer chronometer;
    Button btnReset;
    DataManager dataManager;
    SimpleDateFormat format;
    String currentTime;
    // END MAIN VARS


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign variable
        chronometer = findViewById(R.id.chronometer);
        btnReset = findViewById(R.id.btnReset);

        // Initialize data manager
        dataManager = new DataManager(getApplicationContext());

        // Initialize simple date format
        format = new SimpleDateFormat("hh:mm:ss aa");

        // get current time
        currentTime = format.format(new Date());

        // get flag from data manager
        boolean flag = dataManager.getFlag();

        // check condition
        if(!flag) {
            // when flag is false, set current time
            dataManager.setCurrentTime(currentTime);
            dataManager.setFlag(true);

            // start chronometer
            chronometer.start();
        } else {
            // when flag is true, get data manager current time
            String dataManagerCurrentTime = dataManager.getCurrentTime();

            try {
                // convert string to date
                Date date1 = format.parse(dataManagerCurrentTime);
                Date date2 = format.parse(currentTime);

                // calculate time diff
                long mils = date2.getTime() - date1.getTime();
                chronometer.setBase(SystemClock.elapsedRealtime() - mils);

                // start chronometer
                chronometer.start();
            }catch (ParseException e) {
                e.printStackTrace();
            }
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset chronometer
                chronometer.setBase(SystemClock.elapsedRealtime());
                dataManager.setCurrentTime(currentTime);
                chronometer.start();
            }
        });

    }
}