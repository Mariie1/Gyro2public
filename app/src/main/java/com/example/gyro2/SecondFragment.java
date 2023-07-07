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
    private MyDatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        myRecyclerview = (RecyclerView) v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        myRecyclerview.setLayoutManager(layoutManager);
        myRecyclerview.setAdapter(new MyAdapter(myData));

        db =((GyroApplication) getActivity().getApplication()).getDatabase();
        MyDataDao dataDao = db.myDataDao();
        myData = dataDao.getAllSync();
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView RecyclerViewTitleView = view.findViewById(R.id.recyclerView);

        //MyAdapter adapter = new MyAdapter(myData);
        Bundle args = getArguments();

        SecondFragmentArgs secondFragmentArgs = null;
        if (args != null){
            secondFragmentArgs = SecondFragmentArgs.fromBundle(args);
            db =((GyroApplication) getActivity().getApplication()).getDatabase();
            MyDataDao dataDao = db.myDataDao();
            myData = dataDao.getAllSync();
        }

        if(secondFragmentArgs != null){
            //Ein Recyclerview braucht keinen title oder?
        }
    }
}
