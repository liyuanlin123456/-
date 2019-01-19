package com.example.lenovo.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;

public class MyFragment extends Fragment {

    private ImageView image;
    private TextView name;
    private TextView namee;
    private TextView sex;
    private TextView year;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        namee = view.findViewById(R.id.namee);
        sex = view.findViewById(R.id.sex);
        year = view.findViewById(R.id.year);
    }
}
