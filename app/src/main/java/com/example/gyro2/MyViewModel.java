package com.example.gyro2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
//nicht sicher ob das hier alles passt
public class MyViewModel extends BaseViewModel {
    private MutableLiveData<float[]> myDataLiveData = new MutableLiveData<>();


    public MyViewModel(@NonNull Application application) {
        super(application);

    }

    public void updateData(float[] myData) {
        myDataLiveData.setValue(myData);


    }

    public void setMyData(MyData myData){
        myDataLiveData.setValue(myData);
    }

    public MyData getData(){return myDataLiveData.getValue();}

    public void observeData(LifecycleOwner owner, Observer<MyData> observer){
        myDataLiveData.observe(owner, observer);
    }



}
