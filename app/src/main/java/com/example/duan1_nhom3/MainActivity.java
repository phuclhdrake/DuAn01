package com.example.duan1_nhom3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.duan1_nhom3.ViewPage.Viewpageadapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.duan1_nhom3.Fragment.fragment_tablee;
public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hàm Ánh xạ
        init();

        // Đổ fragment_table vào viewpager

        fragmentManager.beginTransaction().replace(R.id.fragmnet_context, fragment_tablee.newInstance(null,null)).commit();

        // Hàm bắt sự kiện bottomNavigation
        bottomNavigationOnclick();
    }

    // Ánh xạ
    private void init() {
        bottomNavigationView    = findViewById(R.id.bottom_navi);
        fragmentManager         = getSupportFragmentManager();
    }

    // Bắt sự kiện bottomNavigation
    private void bottomNavigationOnclick() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setOnNavigationItemSelectedListener((item) ->{
            switch (item.getItemId()){
                case R.id.nav_Table:
                    fragmentManager.beginTransaction().replace(R.id.fragmnet_context,fragment_tablee.newInstance(null,null)).commit();

                    break;
                case R.id.nav_food:
                    Toast.makeText(MainActivity.this, "food", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_option:
                    Toast.makeText(MainActivity.this, "food", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        });
    }
}