package com.example.mystory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter fragmentPagerAdapter;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyViewPager viewPager = findViewById(R.id.viewPager);
        fragmentPagerAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);

        bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.home);

        frameLayout = findViewById(R.id.frame_layout);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment1()).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch(menuItem.getItemId()){
                    case R.id.home:
                        frameLayout.setVisibility(View.INVISIBLE);
                        viewPager.setVisibility(View.VISIBLE);
                        break;
                    case R.id.explore:
                        frameLayout.setVisibility(View.VISIBLE);
                        viewPager.setVisibility(View.INVISIBLE);
                        fragment = new TestFragment();
                        break;
                    case R.id.add:
                        frameLayout.setVisibility(View.VISIBLE);
                        viewPager.setVisibility(View.INVISIBLE);
                        fragment = new TestFragment();
                        break;
                    case R.id.inbox:
                        frameLayout.setVisibility(View.VISIBLE);
                        viewPager.setVisibility(View.INVISIBLE);
                        fragment = new TestFragment();
                        break;
                    case R.id.profile:
                        frameLayout.setVisibility(View.VISIBLE);
                        viewPager.setVisibility(View.INVISIBLE);
                        fragment = new TestFragment();
                        break;
                }
                if(fragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
                }
                return true;
            }
        });
    }
    public static class PageAdapter extends FragmentPagerAdapter{

        public PageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    //Fragment1
                    return Fragment1.newInstance();
                case 1:
                    //Fragment2
                    return Fragment1.newInstance();
                case 2:
                    //Fragment2
                    return Fragment1.newInstance();
                case 3:
                    //Fragment4
                    return Fragment1.newInstance();
                case 4:
                    //Fragment5
                    return Fragment1.newInstance();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
