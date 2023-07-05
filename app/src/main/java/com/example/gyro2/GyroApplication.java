package com.example.gyro2;

import androidx.room.Room;

public class GyroApplication extends android.app.Application {
    private MyDatabase db;
    public MyDatabase getDatabase(){
        return db;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        db = Room.databaseBuilder(
                getApplicationContext(),
                MyDatabase.class, "my-database").build();
    }




}
