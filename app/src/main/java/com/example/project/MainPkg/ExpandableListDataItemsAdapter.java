package com.example.project.MainPkg;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.project.R;
import com.example.project.SQLite.DBHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import kotlin.Triple;

public class ExpandableListDataItemsAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> expandableTitleList;
    TreeMap<String, List<Triple<String, String, String>>> expandableDetailList;

    private DBHandler dbHandler;
    private ArrayList<ReadModal> itemModalArrayList;

    public ExpandableListDataItemsAdapter(Context context, List<String> expandableListTitle,
                                          TreeMap<String, List<Triple<String, String, String>>> expandableListDetail) {
        this.context = context;
        this.expandableTitleList = expandableListTitle;
        this.expandableDetailList = expandableListDetail;
    }



    @Override
    public int getGroupCount() {
        return this.expandableTitleList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableDetailList.get(this.expandableTitleList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableTitleList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandableDetailList.get(this.expandableTitleList.get(groupPosition)).get(childPosition);    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    /*public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return convertView; NOTE:DOESN'T WORK

    }*/

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        String header = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, parent, false);
        }


        TextView listHeader = (TextView) convertView.findViewById(R.id.header);
        listHeader.setTypeface(null, Typeface.BOLD);
        listHeader.setText(header);

/*        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);*/


        return convertView;
    }


    @Override
    /*public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView; NOTE:DOESN'T WORK
    } */

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String date = (String) getGroup(groupPosition);
        Triple<String, String, String> foodAndAmountPair = expandableDetailList.get(date).get(childPosition);
        String food = ((Triple<String, String, String>) foodAndAmountPair).getFirst();
        String amount = ((Triple<String, String, String>) foodAndAmountPair).getSecond();
        String id = ((Triple<String, String, String>) foodAndAmountPair).getThird();

// String amount = (String) getChild(groupPosition,childPosition);
/* TextView amountTextView = convertView.findViewById(R.id.amounted);
amountTextView.setText(amount); */



/* String amount = (String) getChild(groupPosition,childPosition);
TextView amountTextView = convertView.findViewById(R.id.amounted); */


/* String amount = (String) getChild(groupPosition, childPosition);
Map amountMap = (Map) expandableDetailList.get(expandableTitleList.get(groupPosition));
amountMap.get(amount);*/



        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
        }
        itemModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(convertView.getContext());

        TextView listItemTextView = (TextView) convertView.findViewById(R.id.expandedListItem);
        listItemTextView.setText(food);

        TextView amountTextView = convertView.findViewById(R.id.amounted);
        amountTextView.setText(amount);


        Button button = convertView.findViewById(R.id.remove);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //Set the icon
                builder.setIcon(R.drawable.logo);
                //Set the title
                builder.setTitle("Delete Confirmation");
                //Set the message you want to display
                builder.setMessage("Are you sure, you want to delete this item?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteCourse(id);
                        Toast.makeText(context, "" +id, Toast.LENGTH_SHORT).show();
                        List valueToRemove = expandableDetailList.get(expandableTitleList.get(groupPosition));

                        valueToRemove.remove(childPosition); //CHANGED THIS WHOLE SECTION TO DELETE EVERYTHING

                        if (valueToRemove.isEmpty()) {
                            expandableDetailList.remove(expandableTitleList.get(groupPosition));
                            expandableTitleList.remove(groupPosition);
                        }

                        notifyDataSetChanged();
                    }
                });

                builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }


        });
        return convertView;



        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ValueToRemove = (String) getChild(groupPosition,childPosition);

            }
        }); */


    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
