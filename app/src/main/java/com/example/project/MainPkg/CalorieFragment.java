package com.example.project.MainPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.AddPkg.AddActivity;
import com.example.project.R;
import com.example.project.SQLite.DBHandler;

import java.util.ArrayList;


public class CalorieFragment extends Fragment {

    private DBHandler dbHandler;
    private ArrayList<ReadModal> itemModalArrayList;
    ProgressBar protein, carb, fat;
    TextView calorie;

    private static int totalCalorie, totalCarb, totalFat, totalProtein;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calorie, container, false);

        itemModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());
        protein = view.findViewById(R.id.progressBarProtein);
        carb = view.findViewById(R.id.progressBarCarbs);
        fat = view.findViewById(R.id.progressBarFat);
        calorie = view.findViewById(R.id.totalCalorieTextView);

        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        if(AddActivity.update2) {
            itemModalArrayList = dbHandler.readCourses();
            totalProtein = totalCalorie = totalCarb = totalFat = 0;
            for (ReadModal readModal : itemModalArrayList) {
                if (readModal.date != null) {
                    totalCalorie += Integer.parseInt(readModal.calories) * Integer.parseInt(readModal.amount);
                    totalCarb += Integer.parseInt(readModal.carb) * Integer.parseInt(readModal.amount);
                    totalFat += Integer.parseInt(readModal.fat) * Integer.parseInt(readModal.amount);
                    totalProtein += Integer.parseInt(readModal.protein) * Integer.parseInt(readModal.amount);
                }
            }
            int totalStuff = totalCarb+totalProtein+totalFat;
            protein.setMax(totalStuff);
            protein.setProgress(totalProtein, true);
            fat.setMax(totalStuff);
            fat.setProgress(totalFat, true);
            carb.setMax(totalStuff);
            carb.setProgress(totalCarb, true);
            calorie.setText("Total Calories: " + totalCalorie);
        }
    }
}
