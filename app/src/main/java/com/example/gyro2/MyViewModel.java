package com.example.gyro2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.sql.Struct;
import java.util.ArrayList;

//nicht sicher ob das hier alles passt
public class MyViewModel extends BaseViewModel {
    private MutableLiveData<ArrayList<float[]>> myDataLiveData = new MutableLiveData<>();
   private MutableLiveData<String> textfield = new MutableLiveData<>();

    public MyViewModel(@NonNull Application application) {
        super(application);

    }

    public void updateData(ArrayList<float[]> MyData) {
        myDataLiveData.setValue(MyData);
        textfield.setValue("Initialer Text");

    }

    public void setMyData(ArrayList<float[]> MyData){
        myDataLiveData.setValue(MyData);
    }

    public LiveData<ArrayList<float[]>> getData(){
        return myDataLiveData;
    }

    public void observeData(LifecycleOwner owner, Observer<ArrayList<float[]>> observer){
        myDataLiveData.observe(owner, observer);
    }

    public LiveData<String> getTextfield(){
       return textfield;
    }
    public void setText(String text){
        textfield.setValue(text);
    }

}
