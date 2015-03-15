package com.example.hardik.knapsack.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hardik.knapsack.BL.Expense;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Stranger on 1/7/2015.
 */
public class DataBaseAdapter extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseAdapter";
    private static final String DATABASE_NAME = "sack_database";
    private static final int DATABASE_VERSION = 2;
    //Table EXPENSE
    private static final String TABLE_EXPENSE = "expense";
    private static final String EXPENSE_ID = "e_id";
    private static final String EXPENSE_TYPE = "e_type";
    private static final String EXPENSE_DESC = "e_description";
    private static final String EXPENSE_AMOUNT = "e_amount";
    private static final String Expense_DATE = "e_date";
    private Context mContext;
    private SQLiteDatabase mDataBase;


    public DataBaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL("CREATE TABLE " + TABLE_EXPENSE + " (" + EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + EXPENSE_TYPE + " TEXT," + EXPENSE_AMOUNT + " INTEGER," + EXPENSE_DESC + " TEXT," + Expense_DATE + " TEXT)");
            Log.i(TAG, TABLE_EXPENSE + "Table created");
        } catch (Exception e) {
            Log.e(TAG, "Error in create " + TABLE_EXPENSE + " table : " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Database is updated from " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        onCreate(db);
    }


    public void insetExpense(Expense expense) {

        try {
            mDataBase = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EXPENSE_TYPE, expense.getType());
            values.put(EXPENSE_AMOUNT, expense.getAmount());
            values.put(EXPENSE_DESC, expense.getDescription());
            values.put(Expense_DATE, expense.getDate());

            Log.i(TAG, "Expense Inserted Successfully : " + mDataBase.insert(TABLE_EXPENSE, null, values));
        } catch (Exception e) {
            Log.e(TAG, "Error in insert expense : " + e.getMessage());
        }
    }

    public ArrayList<Expense> getAllExpense() {
        final ArrayList<Expense> expenseList = new ArrayList<Expense>();
        mDataBase = getWritableDatabase();

        try {

            Cursor cursor = mDataBase.rawQuery("SELECT * FROM " + TABLE_EXPENSE, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    final Expense expense = new Expense();
                    expense.setType(cursor.getString(cursor.getColumnIndex(EXPENSE_TYPE)));
                    expense.setAmount(cursor.getInt(cursor.getColumnIndex(EXPENSE_AMOUNT)));
                    expense.setType(cursor.getString(cursor.getColumnIndex(EXPENSE_TYPE)));
                    expense.setDescription(cursor.getString(cursor.getColumnIndex(EXPENSE_DESC)));
                    expense.setDate(cursor.getString(cursor.getColumnIndex(Expense_DATE)));
                    expenseList.add(expense);
                }
            } else {
                Log.e(TAG, "Null cursor in get all expense");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in get all expense : " + e.getMessage());
        }
        return expenseList;
    }

    public ArrayList<Expense> getMonthExpense(int month, int year) {
        final ArrayList<Expense> expenseList = new ArrayList<>();
        mDataBase = getWritableDatabase();
        try {
            Cursor cursor = mDataBase.rawQuery("SELECT * FROM " + TABLE_EXPENSE, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    String[] date = (cursor.getString(cursor.getColumnIndex(Expense_DATE))).split("-");

                    int expMonth = Integer.parseInt(date[1]);
                    int expYear = Integer.parseInt(date[2]);

                    if (month == expMonth && year == expYear) {
                        final Expense expense = new Expense();
                        expense.setType(cursor.getString(cursor.getColumnIndex(EXPENSE_TYPE)));
                        expense.setAmount(cursor.getInt(cursor.getColumnIndex(EXPENSE_AMOUNT)));
                        expense.setType(cursor.getString(cursor.getColumnIndex(EXPENSE_TYPE)));
                        expense.setDescription(cursor.getString(cursor.getColumnIndex(EXPENSE_DESC)));
                        expense.setDate(cursor.getString(cursor.getColumnIndex(Expense_DATE)));
                        expenseList.add(expense);
                    }
                }
            } else {
                Log.e(TAG, "Null cursor in get all expense");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in get all expense per month : " + e.getMessage());
        }
        return expenseList;
    }


    public int getExpenseByType(int month, String type) {
        int result = 0;

        mDataBase = getWritableDatabase();
        Cursor cursor = mDataBase.query(TABLE_EXPENSE, new String[]{Expense_DATE, EXPENSE_AMOUNT},
                EXPENSE_TYPE + " = ?", new String[]{type}, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                String[] date = (cursor.getString(cursor.getColumnIndex(Expense_DATE))).split("-");
                int expMonth = Integer.parseInt(date[1]);
                int expYear = Integer.parseInt(date[2]);
                int amount = cursor.getInt(cursor.getColumnIndex(EXPENSE_AMOUNT));


                Calendar now = Calendar.getInstance();   // Gets the current date and time
                int year = now.get(Calendar.YEAR);

                if (expYear == year && expMonth == month) {
                    result = result + amount;
                }
            }
        }
        return result;
    }

}
