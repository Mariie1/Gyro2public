package com.example.gyro2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;

public class ThirdFragment extends Fragment {
    private SensorManager sensorManager;
    private Sensor sensor;
    private ScrollView scrollView;
    private MyViewModel myViewModel;
    private TextView DataTextView;
    private Observer<ArrayList<float[]>> dataObserver;

    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) requireContext().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        dataObserver = new Observer<ArrayList<float[]>>() {
            @Override
            public void onChanged(ArrayList<float[]> myData) {
                updateUI(myData);
            }
        };
    }
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view =   inflater.inflate(R.layout.fragment_third, container, false);
        DataTextView = view.findViewById(R.id.textView);
        scrollView = view.findViewById(R.id.scrollView);
        return view;
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] linear_acceleration = new float[3];
            linear_acceleration[0] = event.values[0];
            linear_acceleration[1] = event.values[1];
            linear_acceleration[2] = event.values[2];

            ArrayList<float[]> acc_readings = new ArrayList<>();
            acc_readings.add(linear_acceleration);
            myViewModel.insertData(acc_readings);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyDataDao myDataDao = ((GyroApplication) requireActivity().getApplication()).getDatabase().myDataDao();
        MyViewModelFactory factory = new MyViewModelFactory(requireActivity().getApplication(), myDataDao);
        myViewModel = new ViewModelProvider(requireActivity(), factory).get(MyViewModel.class);
        myViewModel.observeData(getViewLifecycleOwner(), new Observer<ArrayList<float[]>>() {
            @Override
            public void onChanged(ArrayList<float[]> myData) {
                updateUI(myData);
            }
        });
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void updateUI(ArrayList<float[]> myData) {
        StringBuilder sb = new StringBuilder();
        for (float[] values : myData) {
            sb.append(Arrays.toString(values)).append("\n");
        }
        DataTextView.setText(sb.toString());
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }
}
