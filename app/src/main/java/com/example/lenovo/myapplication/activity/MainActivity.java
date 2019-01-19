package com.example.lenovo.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.fragment.ClassFragment;
import com.example.lenovo.myapplication.fragment.HomeFragment;
import com.example.lenovo.myapplication.fragment.MyFragment;
import com.example.lenovo.myapplication.fragment.PageFragment;
import com.example.lenovo.myapplication.fragment.ShoppingFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_dashboard1:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_dashboard2:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_dashboard3:
                    viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private HomeFragment homeFragment;
            private ShoppingFragment fragment;

            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new PageFragment();
                    case 1:
                        return new ClassFragment();
                    case 2:
                        homeFragment = new HomeFragment();
                        homeFragment.setSuccessShoppingShow(new HomeFragment.SuccessShoppingShow() {
                            @Override
                            public void onsuccessShoppingShow(String result) {
                                fragment.getResult(result);
                            }
                        });
                        return homeFragment;
                    case 3:
                        fragment = new ShoppingFragment();
                        return fragment;
                    case 4:
                        return new MyFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_dashboard1);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_dashboard2);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_dashboard3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
