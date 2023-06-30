package com.example.gyro2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.metrics.Event;
import android.os.Bundle;

import androidx.annotation.Nullable;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Observer;

public class HostActivity extends AppCompatActivity{

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] linear_acceleration = new float[0];
            linear_acceleration[0] = event.values[0];
            linear_acceleration[1] = event.values[1];
            linear_acceleration[2] = event.values[2];
            MyViewModel.updateData(linear_acceleration);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Sensor registern


        MyData.observe(this, new Observer<MyData>(){
        @Override
        public void onChanged(MyData myData){
            //textfield.setText(myData.myStringData);
        }
        });
    }
    private Observer<MyData> o = new Observer<>(){
        @Override
        public void onChanged(MyData myData){
            //textfield.setText(myData.myStringData);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        MyData.observeForever(o);
        MyViewModel.updateData();
    }
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
        MyData.removeObserver(o);
    }

}
