package com.example.project.MainPkg;

import android.util.Pair;

import com.example.project.MainPkg.ReadModal;
import com.example.project.SQLite.DBHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import kotlin.Triple;


public class ExpandableListDataItems {
    static List<String> newList = new ArrayList<>();

    private DBHandler dbHandler;
    private ArrayList<ReadModal> itemModalArrayList;


    static TreeMap<String, List<Triple<String, String, String>>> expandableDetailList = new TreeMap<>(new DateComparator());

    public static TreeMap<String, List<Triple<String, String, String>>> getData() {
        //  LinkedHashMap<String, ListFragment<String>> expandableDetailList = new LinkedHashMap<String, ListFragment<String>>();

//        ListFragment<String> aprilEight = new ArrayList<String>();
//        aprilEight.add("Apple");
//        aprilEight.add("Orange");
//
//        ListFragment<String> aprilNine = new ArrayList<String>();
//        aprilNine.add("Tomato");
//        aprilNine.add("Potato");
//
//        ListFragment<String> aprilTen = new ArrayList<String>();
//        aprilTen.add("Chicken");
//        aprilTen.add("Lamb");
//
//        ListFragment<String> aprilElven = new ArrayList<String>();
//        aprilElven.add("Milk");
//        aprilElven.add("Cheese");
//
//        ListFragment<String> aprilTwelve = new ArrayList<String>();
//        aprilTwelve.add("Cookie");
//        aprilTwelve.add("Fish");
//
//        ListFragment<String> aprilThirteen = new ArrayList<String>();
//        aprilThirteen.add("Cake");
//        aprilThirteen.add("Soda");
//
//
//        expandableDetailList.put("9/3/24", aprilEight);
//        expandableDetailList.put("9/3/24", aprilNine);
//        expandableDetailList.put("9/3/24", aprilTen);
//        expandableDetailList.put("11/3/24", aprilElven);
//        expandableDetailList.put("9/2/24",aprilTwelve);
//        expandableDetailList.put("11/4/22",aprilThirteen);

        return expandableDetailList;


    }

    public static void Insert(String date, String amount, String food, String id) {

        if (expandableDetailList.containsKey(date)) {
            List<Triple<String, String, String>> items = expandableDetailList.get(date);
            expandableDetailList.remove(date);
            items.add(new Triple<>(food, amount, id));
            expandableDetailList.remove(date);
            expandableDetailList.put(date, items);
        } else {
            List<Triple<String, String, String>> newList = new ArrayList<>();
            newList.add(new Triple<>(food, amount, id));
            expandableDetailList.put(date, newList);

        }
    }

    static class DateComparator implements Comparator<String> {
        @Override
        public int compare(String date1, String date2) {

            String[] parts1 = date1.split("/");
            String[] parts2 = date2.split("/");

            int day1 = Integer.parseInt(parts1[0]);
            int month1 = Integer.parseInt(parts1[1]);
            int year1 = Integer.parseInt(parts1[2]);

            int day2 = Integer.parseInt(parts2[0]);
            int month2 = Integer.parseInt(parts2[1]);
            int year2 = Integer.parseInt(parts2[2]);

            if (year1 != year2) {
                return Integer.compare(year1, year2);
            }

            if (month1 != month2) {
                return Integer.compare(month1, month2);
            }

            return Integer.compare(day1, day2);

        }


    }

}