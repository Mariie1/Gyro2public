package com.example.gyro2;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {MyData.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDataDao myDataDao();
}
