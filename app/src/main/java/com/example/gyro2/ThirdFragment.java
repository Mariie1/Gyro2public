package com.example.gyro2;

import android.annotation.SuppressLint;
import android.content.Context;
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
    private Sensor sensor;
    private MyViewModel myViewModel;
    private MyData myData;

    private Observer o;
    private TextView DataTextView;




    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {


        View view =   inflater.inflate(R.layout.fragment_third, container, false);
        DataTextView = view.findViewById(R.id.textView);
        return view;

    }
    private android.hardware.SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] linear_acceleration = new float[3];
            linear_acceleration[0] = event.values[0];
            linear_acceleration[1] = event.values[1];
            linear_acceleration[2] = event.values[2];

           // System.out.println(linear_acceleration[0]);
            ArrayList<float[]> acc_readings = new ArrayList<>(Arrays.asList(linear_acceleration));
            myViewModel.updateData(acc_readings);

        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);

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
