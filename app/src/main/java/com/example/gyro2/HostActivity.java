package com.example.gyro2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.Nullable;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Observer;

public class HostActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Sensor registern
        sensorManager.registerListener((SensorEventListener) this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    private Observer<MyData> o = new Observer<>() {
        @Override


        public void onSensorChanged(SensorEvent event) {

            float[] linear_acceleration = new float[0];
            linear_acceleration[0] = event.values[0];
            linear_acceleration[1] = event.values[1];
            linear_acceleration[2] = event.values[2];
        }

        @Override
        protected void onResume() {
           // super.onResume();
            sensorManager.registerListener((SensorEventListener) this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            Event.observeForever(o);
            MyViewModel.updateData();


        }


        //das is noch weird
        @Override
        protected void onPause() {
            super.onPause();
         sensorManager.unregisterListener((SensorListener) this);
        }
    }
    //wohin??
    //MyDataDao dataDao = db.userDao();
    //List<MyData> myData = dataDao.getAll();
}
