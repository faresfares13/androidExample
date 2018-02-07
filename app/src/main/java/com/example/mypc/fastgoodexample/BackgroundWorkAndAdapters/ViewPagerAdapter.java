package com.example.mypc.fastgoodexample.BackgroundWorkAndAdapters;

/**
 * Created by Haitham-PC on 1/16/2018.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mypc.fastgoodexample.Fragments.AllRecipes;
import com.example.mypc.fastgoodexample.Fragments.QuickAndEasy;
import com.example.mypc.fastgoodexample.Fragments.SlowCooker;
import com.example.mypc.fastgoodexample.Fragments.Vegan;
import com.example.mypc.fastgoodexample.Fragments.Vegetarian;

public class ViewPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Vegetarian getItem(int position) {
        if (position ==0) {
            return new AllRecipes();
        } else if (position == 1) {
            return new Vegetarian();
        } else if(position == 2) {
            return new Vegan();
        }else if(position==3){
            return new QuickAndEasy();
        }else return new SlowCooker();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
