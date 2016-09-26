package com.example.android.shockdetect;

import java.text.DateFormat;
import java.util.Date;
import java.lang.Object;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;


import org.json.*;
import java.net.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xText, yText, zText, Event;
    private Sensor mS;
    private SensorManager SM;

    private TextView tOutput;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create our sensor manager
        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Accelerometer Sensor
        mS = SM.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        //Register sensor Listener
        SM.registerListener(this, mS, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TExt Views
        xText = (TextView) findViewById(R.id.xText);
        yText = (TextView) findViewById(R.id.yText);
        zText = (TextView) findViewById(R.id.zText);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText("X: "+event.values[0]);
        yText.setText("Y: "+event.values[1]);
        zText.setText("Z: "+event.values[2]);
        if ((event.values[0]>5) || (event.values[1]>5) || (event.values[2]>5)){
            float max=event.values[0];
            if(max<event.values[1]){
                max=event.values[1];
            }
            if(max<event.values[2])
            {
                max=event.values[2];
            }
            Date d = new Date();
            tOutput = (TextView) findViewById(R.id.result);
            tOutput.setText("The incident time is: "+d.toString());
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Not in use
    }
}
