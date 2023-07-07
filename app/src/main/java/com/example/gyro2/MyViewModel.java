package com.example.gyro2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

//nicht sicher ob das hier alles passt
public class MyViewModel extends BaseViewModel {
    private MutableLiveData<ArrayList<float[]>> myDataLiveData = new MutableLiveData<>();
   private MutableLiveData<String> textfield = new MutableLiveData<>();
    private MyDataDao myDataDao;
    public MyViewModel(@NonNull Application application, MyDataDao myDataDao) {
        super(application);
        this.myDataDao = myDataDao;

    }


    public void updateData(ArrayList<float[]> MyData) {
        myDataLiveData.setValue(MyData);


    }
    public void insertData(ArrayList<float[]> myData) {
        // ...

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (float[] data : myData) {
                    StringBuilder sb = new StringBuilder();
                    for (float value : data) {
                        sb.append(value).append(" ");
                    }
                    MyData myDataObject = new MyData(sb.toString());
                    myDataDao.insertAll(myDataObject);
                }
                // Datenbankabfrage, um die aktualisierten Daten abzurufen und in die LiveData zu übertragen
                List<MyData> newData = myDataDao.getAllSync(); // Synchroner Datenbankzugriff
                ArrayList<float[]> updatedData = new ArrayList<>();
                for (MyData dataObject : newData) {
                    // Daten in den ursprünglichen float[]-Format umwandeln
                    String[] values = dataObject.data.split(" ");
                    float[] valuesArray = new float[values.length];
                    for (int i = 0; i < values.length; i++) {
                        valuesArray[i] = Float.parseFloat(values[i]);
                    }
                    updatedData.add(valuesArray);
                }
                myDataLiveData.postValue(updatedData); // Post the value on the main thread
            }
        }).start();
    }

    public void setMyData(ArrayList<float[]> MyData){
        myDataLiveData.setValue(MyData);
    }

    public LiveData<ArrayList<float[]>> getLiveData(){

        return myDataLiveData;
    }

    public void observeData(LifecycleOwner owner, Observer observer){
        myDataLiveData.observe(owner, observer);
    }

    public LiveData<String> getTextfield(){

        return textfield;
    }
    public void setText(String  text){

        textfield.setValue(text);
    }

}
