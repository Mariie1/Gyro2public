package com.example.gyro2;

import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;



public class MockSensor{

    private SensorEvent event;
    public void start(
            Context c, long intervalMs, SensorEventListener listener) {
        Thread t = new Thread(() -> {
            String[][] csvData = readCsvData();
            for (String[] record : csvData) {
                listener.onSensorChanged(event);
                try {
                    Thread.sleep(intervalMs);
                } catch (InterruptedException e) {
                }
            }
        });
        t.start();

    }
    public void stop() {
// TODO: implement!
    }
}