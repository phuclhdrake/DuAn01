package com.example.duan1.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.duan1.fragment.FragmentCombo;
import com.example.duan1.fragment.FragmentFastFood;
import com.example.duan1.fragment.FragmentWater;

public class ViewPagerFragmentFood extends FragmentStatePagerAdapter {

    public ViewPagerFragmentFood(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new FragmentFastFood();
            case 1: return new FragmentWater();
            case 2: return new FragmentCombo();
            default: return new FragmentFastFood();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
