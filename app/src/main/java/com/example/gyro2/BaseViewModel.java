package com.example.gyro2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.RoomDatabase;

public abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);

    }

    public MyDatabase getDatabase(){
        return((com.example.gyro2.Application) getApplication()).getDatabase();
    }
}
