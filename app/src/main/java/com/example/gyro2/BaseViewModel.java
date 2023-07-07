package com.example.gyro2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);

    }

    public MyDatabase getDatabase(){

        return((GyroApplication) getApplication()).getDatabase();
    }
}
