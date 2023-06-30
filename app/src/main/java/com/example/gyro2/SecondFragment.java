package com.example.gyro2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import java.util.List;

public class SecondFragment extends Fragment {
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
            Database db = Application.getDatabase();
            MyDataDao dataDao = db.userDao();
            List<MyData> myData = dataDao.getAll();

        }

        if(secondFragmentArgs != null){
            //Ein Recyclerview braucht keinen title oder? lol
        }

    }
}
