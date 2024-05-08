package com.example.project.MainPkg;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Menu_Adapter extends FragmentStateAdapter {
    public Menu_Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {

            case 0: {
                return new CalorieFragment();
            }
            case 1: {
                return new HomepageFragment();
            }
            case 2: {
                return new CalendarFragment();
            }
            case 3: {
                return new ListFragment();
            }

            default: {
                return null;
            }
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}

