package com.example.project.MainPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.AddPkg.AddActivity;
import com.example.project.R;
import com.example.project.SQLite.DBHandler;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {

    Button addAct;
    RecyclerView recyclerView;
    CalendarView calendarView;
    private DBHandler dbHandler;
    private ArrayList<ReadModal> itemModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ArrayList<Item_Modal> arrayList = new ArrayList<>();
//        arrayList.add(new Item_Modal(R.drawable.logo,"Broccoli","100g"));
//        arrayList.add(new Item_Modal(R.drawable.logo,"Potato","200g"));
//        arrayList.add(new Item_Modal(R.drawable.logo,"Apple","2pc"));
//        arrayList.add(new Item_Modal(R.drawable.logo,"Lime","30g"));
        itemModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getActivity());


        addAct = view.findViewById(R.id.bt_add);
        recyclerView = view.findViewById(R.id.rv_items);
        calendarView = view.findViewById(R.id.calendarView);

        Intent intentAdd = new Intent(getActivity(), AddActivity.class);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //Read data from database and put it in a readModal ArrayList
                itemModalArrayList = dbHandler.readCourses();
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
                ArrayList<Item_Modal> arrayList = new ArrayList<>();
                //for all ReadModal
                for (ReadModal readModal : itemModalArrayList) {
                    if (readModal.date != null && readModal.date.equals(date) && readModal.name != null && readModal.amount != null) {
                        arrayList.add(new Item_Modal(R.drawable.logo, readModal.name, readModal.amount));
                    }
                }
                if (arrayList.isEmpty()) {
                    arrayList.add(new Item_Modal(R.drawable.logo, "No items found :(", "Add one!"));
                }
                Item_Adapter adapter = new Item_Adapter(getActivity(), arrayList);
                recyclerView.setAdapter(adapter);
            }
        });

        addAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentAdd);
            }
        });

        Item_Adapter adapter = new Item_Adapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        //Linear Layout Horizontal
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true);
//        recyclerView.setLayoutManager(linearLayoutManager);

        //Grid Layout
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);

        //Staggered Layout
//         StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL );
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        return view;
    }
}
