package com.example.gyro2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class SecondFragment extends Fragment {

    private List<MyData> myData;
    //private String[] myData;
    private MyDataDao myDataDao;
    private RecyclerView myRecyclerview;
    //private MyDatabase db;
    private MyAdapter myAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        myRecyclerview = v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        myRecyclerview.setLayoutManager(layoutManager);
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
       // RecyclerView RecyclerViewTitleView = view.findViewById(R.id.recyclerView);
        super.onViewCreated(v, savedInstanceState);

        MyDatabase db = ((GyroApplication) getActivity().getApplication()).getDatabase();
        myDataDao = db.myDataDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myData = myDataDao.getLastNData(30);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter = new MyAdapter(myData);
                        myRecyclerview.setAdapter(myAdapter);

                    }
                });
            }
        }).start();




    }
}
