package com.example.mypc.fastgoodexample.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.mypc.fastgoodexample.BackgroundWorkAndAdapters.CustomAdapter;
import com.example.mypc.fastgoodexample.DatabaseManager.DatabaseAccess;
import com.example.mypc.fastgoodexample.R;

public class Vegetarian extends Fragment {
    GridView gridview;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_vegetarian, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this.getActivity());
        databaseAccess.open();
       gridview = (GridView) view.findViewById(R.id.customgrid);
       gridview.setAdapter(new CustomAdapter(this.getActivity(),
              databaseAccess.getVegetarianRecipeName(),
              databaseAccess.getVegetarianImages()));
       databaseAccess.close();
    }
}