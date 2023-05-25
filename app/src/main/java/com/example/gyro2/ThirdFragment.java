package com.example.gyro2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView feedbackTitleView = view.findViewById(R.id.textView);

        Bundle args = getArguments();
        ThirdFragmentArgs thirdFragmentArgs = null;
        if (args != null){
            thirdFragmentArgs = ThirdFragmentArgs.fromBundle(args);
        }

        if(thirdFragmentArgs != null){
            feedbackTitleView.setText(thirdFragmentArgs.getFeedbackTitle());
        }
    }
}
