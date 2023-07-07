package com.example.gyro2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class HostActivity extends AppCompatActivity{

    private SensorManager sensorManager;
    private Sensor sensor;
    private MyViewModel myViewModel;
    private TextView textfield;
    private  MyData myData;
    private SensorEventListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(  sensor != null)
        {
            sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        }

        //Sensor registern
        //MyData.observe(this, new Observer<MyData>(){
        //@Override
        //public void onChanged(MyData myData){
            //textfield.setText(myData.myStringData);
        //}
        //});
    }
    public void unregisterSensor() {
        if (sensorManager != null && listener != null) {
            sensorManager.unregisterListener(listener);
        }
    }
    /*private Observer<ArrayList<float[]>> o = new Observer<ArrayList<float[]>>() {

       @Override
       public void onChanged(MyData myData) {
           text-field.setText((CharSequence) myData);
       }
   };*/
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        /* myViewModel.observeData(getViewLifecycleOwner(),o);
        myViewModel.updateData(myData);
         */
    }
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);

    }




}
