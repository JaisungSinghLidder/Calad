package com.example.project.SQLite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.project.MainPkg.ReadModal;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "itemdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "useritems";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String DATE_COL = "date";

    private static final String NAME_COL = "name";

    // below variable id for our course duration column.
    private static final String AMOUNT_COL = "amount";
    // below variable for our course description column.
    private static final String CALORIES_COL = "calories";
    private static final String PROTEIN_COL = "proteins";
    private static final String CARB_COL = "carbs";
    private static final String FAT_COL = "fats";
    private static final String ITEM_ID_COL = "item";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_COL + " TEXT,"
                + NAME_COL + " TEXT,"
                + AMOUNT_COL + " TEXT,"
                + CALORIES_COL + " TEXT,"
                + PROTEIN_COL + " TEXT,"
                + CARB_COL + " TEXT,"
                + FAT_COL + " TEXT,"
                + ITEM_ID_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

//    public String getID()
    // this method is use to add new course to our sqlite database.
    public void addNewItem(String itemDate, String itemName, String itemAmount, String itemCalories, String itemProtein, String itemCarb, String itemFat, String itemId ) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(DATE_COL, itemDate);
        values.put(NAME_COL, itemName);
        values.put(AMOUNT_COL, itemAmount);
        values.put(CALORIES_COL, itemCalories);
        values.put(PROTEIN_COL, itemProtein);
        values.put(CARB_COL, itemCarb);
        values.put(FAT_COL, itemFat);
        values.put(ITEM_ID_COL, itemId);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);
        // "TABLE_NAME" is the name of the table.
        // null indicates that you have a primary key column set up for auto-increment).
        // values is a ContentValues object containing the data you want to insert into the table.

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public ArrayList<ReadModal> readCourses() {
        // Step 1: on below line we are opening the
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        //  Step2: A Cursor in Android is an interface for accessing and retrieving data
        //  from a database query result. In this case, it will be used to store the result of the SQL query.
        //Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COL + "=?", new String[]{courseName});
        Cursor cursorItems = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        //The second parameter is an array of strings that can be
        // used to replace placeholders in the SQL query with actual values

        // on below line we are creating a new array list.
        ArrayList<ReadModal> courseModalArrayList = new ArrayList<>();

        while(cursorItems.moveToNext()) {
            // on below line we are adding the data from cursor to our array list.
            courseModalArrayList.add(new ReadModal(cursorItems.getString(1),
                    cursorItems.getString(2),
                    cursorItems.getString(3),
                    cursorItems.getString(4),
                    cursorItems.getString(5),
                    cursorItems.getString(6),
                    cursorItems.getString(7),
                    cursorItems.getString(8)));
            Log.w("ListUpdate##########", "Processed");

        }
        // at last closing our cursor
        // and returning our array list.
        cursorItems.close();
        return courseModalArrayList;
    }



    public ArrayList<ReadModal> readLast(int num) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorItems = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<ReadModal> courseModalArrayList = new ArrayList<>();

        // Move the cursor to the desired position
        int count = cursorItems.getCount();
        int targetPosition = count - (num + 1);
        if (targetPosition >= 0 && cursorItems.moveToPosition(targetPosition)) {
            do {
                // Add data from the cursor to the array list
                courseModalArrayList.add(new ReadModal(cursorItems.getString(1),
                        cursorItems.getString(2),
                        cursorItems.getString(3),
                        cursorItems.getString(4),
                        cursorItems.getString(5),
                        cursorItems.getString(6),
                        cursorItems.getString(7),
                        cursorItems.getString(8)));
                Log.w("ListUpdate##########", "Processed");
            } while (cursorItems.moveToNext());
        }

        // Close the cursor
        cursorItems.close();
        return courseModalArrayList;
    }

    public void deleteCourse(String itemId) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
//        db.delete(TABLE_NAME, ITEM_ID_COL, new String[]{itemId});
        db.delete(TABLE_NAME, "item=?", new String[]{itemId});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int getCount() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorItems = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursorItems.getCount();
    }
}
