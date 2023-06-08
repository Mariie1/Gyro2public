package com.example.gyro2;

import android.content.Context;
import android.hardware.SensorListener;

//public class MockSensor {
//    public void start(
//            Context c, long intervalMs, SensorListener l) {
//        Thread t = new Thread(() -> {
//            String[][] csvData = readCsvData();
//            for (String[] record : csvData) {
//                l.onSensorChanged(record);
//                try {
//                    Thread.sleep(intervalMs);
//                } catch (InterruptedException e) {
//                }
//            }
//        });
//        t.start();
//
//
//    }
