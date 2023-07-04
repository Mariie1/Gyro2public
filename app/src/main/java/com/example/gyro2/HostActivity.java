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
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] linear_acceleration = new float[0];
            linear_acceleration[0] = event.values[0];
            linear_acceleration[1] = event.values[1];
            linear_acceleration[2] = event.values[2];
            myViewModel.updateData(linear_acceleration);
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
        //MyData.observe(this, new Observer<MyData>(){
        //@Override
        //public void onChanged(MyData myData){
            //textfield.setText(myData.myStringData);
        //}
        //});
    }
       /*private Observer<MyData> o = new Observer<>(){

        @Override
        public void onChanged(MyData myData){
            textfield.setText((CharSequence) myData);
        }*/
    //};

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        //myData.observeForever(o);
        myViewModel.updateData(myData);
    }
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
        //myData.removeObserver(o);
    }

}
