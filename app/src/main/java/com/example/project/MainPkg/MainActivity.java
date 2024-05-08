package com.example.project.MainPkg;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project.R;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    static String username = "";
    public static String getUsername(){
        return username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab1);
        viewPager2 = findViewById(R.id.viewpager2);


        //starts notification intent, if it's the preselected day to send them
        if(NotificationReminder.isNotificationDay()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(new Intent(MainActivity.this, NotificationReminder.class));
            }
        }

        Intent intent = getIntent();

        username = intent.getStringExtra("Username");

        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Calorie");
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Home");
        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Calendar");
        tabLayout.addTab(thirdTab);

        TabLayout.Tab fourthTab = tabLayout.newTab();
        fourthTab.setText("List");
        tabLayout.addTab(fourthTab);


        Menu_Adapter menu_adapter = new Menu_Adapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(menu_adapter);

        viewPager2.setCurrentItem(1);
        tabLayout.selectTab(secondTab, true);

        // Set up a listener to switch between tabs and pages
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Unused, but can be used for additional functionality
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Unused, but can be used for additional functionality
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


    }
}