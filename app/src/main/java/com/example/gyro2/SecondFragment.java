package com.example.gyro2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

public class SecondFragment extends Fragment {

    private LiveData<MyData> myData;
    private MyDataDao myDataDao;
    private MyDatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView RecyclerViewTitleView = view.findViewById(R.id.recyclerView);

        Bundle args = getArguments();

        SecondFragmentArgs secondFragmentArgs = null;
        if (args != null){
            secondFragmentArgs = SecondFragmentArgs.fromBundle(args);
            db =((GyroApplication) getActivity().getApplication()).getDatabase();
            MyDataDao dataDao = db.myDataDao();
             myData = dataDao.getAll();
        }

        if(secondFragmentArgs != null){
            //Ein Recyclerview braucht keinen title oder?
        }
    }
}
