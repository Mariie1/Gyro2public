package com.example.gyro2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;
import java.util.Observable;


public class HostActivity extends AppCompatActivity{

    private SensorManager sensorManager;
    private Sensor sensor;
    private MyViewModel myViewModel;
    private TextView textfield;
    private  float[] myData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //Sensor registern
        //MyData.observe(this, new Observer<MyData>(){
        //@Override
        //public void onChanged(MyData myData){
            //textfield.setText(myData.myStringData);
        //}
        //});
    }





}
