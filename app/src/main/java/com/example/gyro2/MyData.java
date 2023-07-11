package com.example.gyro2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_data")
public class MyData {
    @PrimaryKey
    public Integer id;
    @ColumnInfo(name = "data")
    public String data;

    public MyData(String data) {
        this.data= data;
    }
}
