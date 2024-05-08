package com.example.project.AddPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project.MainPkg.MainActivity;
import com.example.project.R;
import com.example.project.MainPkg.ReadModal;
import com.example.project.SQLite.DBHandler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;

public class AddActivity extends AppCompatActivity {


    private DBHandler dbHandler;
    Button add, back;
    EditText amount;
    EditText date;
    Spinner typeSpinner;
    static String name;
    static String calorie;
    static String protein;
    static String carb;
    static String fat;
    RecyclerView recyclerView;
    ArrayList<Add_Modal> fruits = new ArrayList<>();
    ArrayList<Add_Modal> vegetables = new ArrayList<>();
    ArrayList<Add_Modal> meats = new ArrayList<>();
    ArrayList<Add_Modal> dairies =new ArrayList<>();
    ArrayList<Add_Modal> spices =new ArrayList<>();
    ExpandableListView expandableListViewExample;
    ExpandableListAdapter expandableListAdapter;
    java.util.List<String> expandableTitleList;
    TreeMap<String, List<String>> expandableDetailList;
    private ArrayList<ReadModal> itemModalArrayList;

    public static boolean update1 = true;
    public static int count1 = 0;
    public static boolean update2 = true;
    public static int count2 = 0;
    public static int item_id;
    public static boolean onStart2 = true;
    public static boolean onStart1 = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHandler = new DBHandler(AddActivity.this);
        add = findViewById(R.id.button);
        //name = findViewById(R.id.AddName);
        amount =findViewById(R.id.AddAmount);
        date = findViewById(R.id.AddDate);
        typeSpinner = findViewById(R.id.AddTypeSpinner);
        recyclerView = findViewById(R.id.AddRecycler);
        itemModalArrayList = new ArrayList<>();
        back = findViewById(R.id.backButton);

//        com.example.project.MainPkg.ListFragment list = new com.example.project.MainPkg.ListFragment();

//        expandableListViewExample = (ExpandableListView) list.getActivity().findViewById(R.id.expandableListViewSample);
//        expandableDetailList = ExpandableListDataItems.getData();
//        expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
//        expandableListAdapter = new ExpandableListDataItemsAdapter(AddActivity.this, expandableTitleList, expandableDetailList);

        String[] types = {"Fruits", "Vegetables", "Meats", "Dairies"};

//        fruits.add(new Add_Modal("Apple", "20", R.drawable.deselected, "0", "1", "0"));
//        fruits.add(new Add_Modal("Orange", "10", R.drawable.deselected, "0", "2", "0"));
//        fruits.add(new Add_Modal("Watermelon", "40", R.drawable.deselected, "0", "2", "0"));
        vegetables.add(new Add_Modal("Brocolli", "5", R.drawable.deselected, "0", "5", "0"));
        vegetables.add(new Add_Modal("Peas", "1", R.drawable.deselected, "0", "10", "0"));
//        meats.add(new Add_Modal("Chicken", "50", R.drawable.deselected, "10", "0", "0"));
//        meats.add(new Add_Modal("Beef", "100", R.drawable.deselected, "10", "0", "10"));
//        meats.add(new Add_Modal("Pork", "200", R.drawable.deselected, "10", "0", "5"));
        fruits.add(new Add_Modal("Apple", "20", R.drawable.deselected, "0", "5", "0"));
        fruits.add(new Add_Modal("Guava", "15", R.drawable.deselected, "2", "3", "1"));
        fruits.add(new Add_Modal("Pear", "25", R.drawable.deselected, "4", "5", "2"));
        fruits.add(new Add_Modal("Banana", "40", R.drawable.deselected, "8", "12", "0"));
        fruits.add(new Add_Modal("Strawberry", "10", R.drawable.deselected, "0", "2", "1"));
        fruits.add(new Add_Modal("Cantaloupe", "30", R.drawable.deselected, "3", "3", "0"));
        fruits.add(new Add_Modal("Watermelon", "50", R.drawable.deselected, "0", "1", "0"));
        fruits.add(new Add_Modal("Dragonfruit", "40", R.drawable.deselected, "8", "10", "2"));
        vegetables.add(new Add_Modal("Carrot", "30", R.drawable.deselected, "0", "3", "0"));
        vegetables.add (new Add_Modal("Cucumber", "60", R.drawable.deselected, "0", "10", "0"));
        vegetables.add (new Add_Modal("Pear", "25", R.drawable.deselected, "4", "5", "2"));
        vegetables.add (new Add_Modal("Spinach", "45", R.drawable.deselected, "8", "12", "0"));
        vegetables.add (new Add_Modal("Kale", "10", R.drawable.deselected, "0", "2", "0"));
        vegetables.add (new Add_Modal("Seaweed", "5", R.drawable.deselected, "5", "3", "0"));
        vegetables.add (new Add_Modal("Beets", "10", R.drawable.deselected, "0", "4", "0"));
        vegetables.add (new Add_Modal("Cauliflower", "40", R.drawable.deselected, "8", "3", "2"));
        meats.add(new Add_Modal("Chicken", "120", R.drawable.deselected, "30", "0", "5"));
        meats.add(new Add_Modal("Lamb", "150", R.drawable.deselected, "20", "0", "0"));
        meats.add(new Add_Modal("Salmon", "130", R.drawable.deselected, "22", "0", "10"));
        meats.add(new Add_Modal("Pork", "160", R.drawable.deselected, "8", "12", "0"));
        meats.add(new Add_Modal("Beef", "200", R.drawable.deselected, "40", "5", "0"));
        meats.add(new Add_Modal("Turkey", "150", R.drawable.deselected, "5", "3", "0"));
        meats.add(new Add_Modal("Venison", "10", R.drawable.deselected, "0", "4", "0"));
        meats.add(new Add_Modal("Bison", "40", R.drawable.deselected, "8", "3", "2"));
        dairies.add(new Add_Modal("Milk", "40", R.drawable.deselected, "8", "3", "2"));
        dairies.add(new Add_Modal("Cheddar", "40", R.drawable.deselected, "8", "3", "2"));
        dairies.add(new Add_Modal("Curd", "40", R.drawable.deselected, "8", "3", "2"));
        dairies.add(new Add_Modal("Cream Cheese", "40", R.drawable.deselected, "8", "3", "2"));
        dairies.add(new Add_Modal("Milk", "40", R.drawable.deselected, "8", "3", "2"));
        dairies.add(new Add_Modal("Mozzarella", "40", R.drawable.deselected, "8", "3", "2"));


        Add_Adapter meatAdapter = new Add_Adapter(this, meats);
        Add_Adapter vegetableAdapter = new Add_Adapter(this, vegetables);
        Add_Adapter fruitAdapter = new Add_Adapter(this, fruits);
        Add_Adapter dairyAdapter = new Add_Adapter(this, dairies);

        recyclerView.setAdapter(fruitAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, types);
        typeSpinner.setAdapter(typeAdapter);

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                switch (selected){
                    case "Fruits":{
                        recyclerView.setAdapter(fruitAdapter);
                        break;
                    }
                    case "Vegetables":{
                        recyclerView.setAdapter(vegetableAdapter);
                        break;
                    }
                    case "Meats":{
                        recyclerView.setAdapter(meatAdapter);
                        break;
                    }
                    case "Dairies":{
                        recyclerView.setAdapter(dairyAdapter);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ItemName = ""+name;
                String ItemAmount = amount.getText().toString();
                String ItemDate = date.getText().toString();
                String ItemCalories = ""+calorie;
                String ItemProtein = ""+protein;
                String ItemCarb = ""+carb;
                String ItemFat = ""+fat;

                Calendar today = Calendar.getInstance();
                String[] dateParts = ItemDate.split("/");

                if(dateParts[1].equals("") || dateParts[0].equals("") || dateParts[2].equals("")){
                    Toast.makeText(AddActivity.this, "Please enter all the values in a date format.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int enteredDay = Integer.parseInt(dateParts[0]);
                int enteredMonth = Integer.parseInt(dateParts[1]);
                int enteredYear = Integer.parseInt(dateParts[2]);

                int currentDay = today.get(Calendar.DAY_OF_MONTH);
                int currentMonth = today.get(Calendar.MONTH) + 1;
                int currentYear = today.get(Calendar.YEAR);

                if (enteredYear < currentYear ||
                        (enteredYear == currentYear && enteredMonth < currentMonth) ||
                        (enteredYear == currentYear && enteredMonth == currentMonth && enteredDay < currentDay))
                {

                    Toast.makeText(AddActivity.this, "Please enter a date that is today or later.", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(enteredDay > 31 || enteredMonth > 12)
                {
                    Toast.makeText(AddActivity.this, "Please enter a date that is possible", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ItemName.isEmpty() || ItemAmount.isEmpty() || ItemDate.isEmpty() || ItemProtein.isEmpty() || ItemCarb.isEmpty() || ItemFat.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                item_id++;
                String ItemId = "" + item_id;
                dbHandler.addNewItem(ItemDate, ItemName, ItemAmount, ItemCalories, ItemProtein, ItemCarb, ItemFat, ItemId);

                Toast.makeText(AddActivity.this, "Item has been added.", Toast.LENGTH_SHORT).show();
                amount.setText("");
                date.setText("");
                Add_Adapter.selected[0] = false;

                String selected = typeSpinner.getSelectedItem().toString();
                switch (selected){
                    case "Fruits":{
                        recyclerView.setAdapter(fruitAdapter);
                        break;
                    }
                    case "Vegetables":{
                        recyclerView.setAdapter(vegetableAdapter);
                        break;
                    }
                    case "Meats":{
                        recyclerView.setAdapter(meatAdapter);
                        break;
                    }
                    case "Dairies":{
                        recyclerView.setAdapter(dairyAdapter);
                        break;
                    }
                }

                update1 = true;
                count1++;
                update2 = true;
                count2++;
//                itemModalArrayList = dbHandler.readCourses();
//                for (ReadModal readModal : itemModalArrayList) {
//                    if (readModal.date != null) {
//                        ExpandableListDataItems.Insert(readModal.date, readModal.amount);
//                    }
//                }
//                expandableDetailList = ExpandableListDataItems.getData();
//                expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
//                expandableListAdapter = new ExpandableListDataItemsAdapter(AddActivity.this, expandableTitleList, expandableDetailList);
//                expandableListViewExample.setAdapter(expandableListAdapter);
            }
        });
        Intent intent = getIntent();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });
    }
}
