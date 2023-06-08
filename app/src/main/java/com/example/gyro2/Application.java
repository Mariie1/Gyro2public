package com.example.gyro2;

import androidx.room.Room;

import java.util.List;

public class Application extends android.app.Application {
    private MyDatabase db;

    @Override
    public void onCreate(){
        super.onCreate();
        db = Room.databaseBuilder(
                getApplicationContext(),
                MyDatabase.class, "my-database").build();
    }

    public MyDatabase getDatabase(){
        return db;
    }


}
