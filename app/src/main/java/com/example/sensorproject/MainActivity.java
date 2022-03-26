package com.example.sensorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.app.usage.UsageEvents;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        final Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener sensorEventListener = new SensorEventListener() {
            private SensorEvent event;

            @Override
            public void onSensorChanged( SensorEvent event) {
                this.event = event;

                if (event.values[0] <proximitySensor.getMaximumRange()){

                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);

                }
                else {

                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(sensorEventListener,proximitySensor,2 * 1000 * 1000);
    }
}