package com.example.gyro2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDataDao {
    @Query("SELECT * FROM my_data")
    List<MyData> getAllSync();

    @Query("SELECT * FROM my_data ORDER BY id DESC LIMIT :limit")
    List<MyData> getLastNData(int limit);

    @Insert
    void insertAll(MyData myData);
}
