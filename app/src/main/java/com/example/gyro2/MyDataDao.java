package com.example.gyro2;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MyDataDao {
    @Query("SELECT * FROM my_data")
    public List<MyData> getAll();
}
