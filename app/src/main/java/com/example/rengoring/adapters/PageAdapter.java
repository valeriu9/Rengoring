package com.example.rengoring.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.rengoring.View.Request.RequestTab;
import com.example.rengoring.View.TimeTableTab;
import com.example.rengoring.View.TodayShiftTab;

public class PageAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;

    public PageAdapter(@NonNull FragmentManager fm, int numberOfTabs) {
        //Avoiding deprecated method by having BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numberOfTabs = numberOfTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimeTableTab();
            case 1:
                return new TodayShiftTab();
            case 2:
                return new RequestTab();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
