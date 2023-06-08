package com.example.gyro2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface MyDataDao {
    @Query("SELECT * FROM my_data")
    public LiveData<List<MyData>> getAll();

    //@Query("SELECT * FROM my_data WHERE id=:id")


    @Insert
    void insertAll(MyData... myData);

    //public LiveData<Array<MyData>> getMyData(Integer id);


}
