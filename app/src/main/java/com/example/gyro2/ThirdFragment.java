package com.example.gyro2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.Arrays;

public class ThirdFragment extends Fragment {
    private SensorManager sensorManager;
    private MyViewModel myViewModel;
    private MyData myData;
    private TextView textfield;
    private Observer o;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_third, container, false);

    }
    private android.hardware.SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] linear_acceleration = new float[3];
            linear_acceleration[0] = event.values[0];
            linear_acceleration[1] = event.values[1];
            linear_acceleration[2] = event.values[2];

            System.out.println(linear_acceleration[0]);
            ArrayList<float[]> acc_readings = new ArrayList<>(Arrays.asList(linear_acceleration));
            myViewModel.updateData(acc_readings);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    /*private Observer<ArrayList<float[]>> o = new Observer<ArrayList<float[]>>() {

        @Override
        public void onChanged(MyData myData) {
            textfield.setText((CharSequence) myData);
        }
    };*/
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        myViewModel.observeData(getViewLifecycleOwner(),o);
        //myViewModel.updateData(myData);
    }
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView feedbackTitleView = view.findViewById(R.id.textView);

        Bundle args = getArguments();
        ThirdFragmentArgs thirdFragmentArgs = null;
        if (args != null){
            thirdFragmentArgs = ThirdFragmentArgs.fromBundle(args);
        }

        if(thirdFragmentArgs != null){
            feedbackTitleView.setText(thirdFragmentArgs.getFeedbackTitle());
        }
    }
}
