package com.example.duan1_nhom3.ViewPage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1_nhom3.Fragment.fragment_table;

public class Viewpageadapter extends FragmentStateAdapter {


    public Viewpageadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new fragment_table();
            case 1:
                return new fragment_table();
            case 2:
                return new fragment_table();
            default:
                return new fragment_table();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}