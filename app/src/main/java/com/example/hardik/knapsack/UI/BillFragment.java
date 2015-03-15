package com.example.hardik.knapsack.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.hardik.knapsack.BL.Global;
import com.example.hardik.knapsack.DataBase.DataBaseAdapter;
import com.example.hardik.knapsack.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Stranger on 1/8/2015.
 */
public class BillFragment extends Fragment {

    ArrayList<String> mListMonth;
    HashMap<String, ArrayList<String>> mBillItem;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        prepareListData();
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.expanded_bill);
        BillAdapter adapter = new BillAdapter(getActivity(), mListMonth, mBillItem);
        expandableListView.setAdapter(adapter);

        return view;
    }


    private void prepareListData() {
        mListMonth = new ArrayList<String>();
        mBillItem = new HashMap<String, ArrayList<String>>();
        DataBaseAdapter dbAdapter = new DataBaseAdapter(getActivity());

        // Adding child data
        mListMonth.add("January");
        mListMonth.add("February");
        mListMonth.add("march");
        mListMonth.add("April");
        mListMonth.add("May");
        mListMonth.add("June");
        mListMonth.add("July");
        mListMonth.add("August");
        mListMonth.add("September");
        mListMonth.add("October");
        mListMonth.add("November");
        mListMonth.add("December");

        ArrayList<String> expenseList = Global.getExpenseType();

        for (int i = 1; i <= 12; i++) {
            ArrayList<String> list = new ArrayList<>();
            list.add("Food : " + dbAdapter.getExpenseByType(i, expenseList.get(0)));
            list.add("Travel :" + dbAdapter.getExpenseByType(i, expenseList.get(1)));
            list.add("Petrol : " + dbAdapter.getExpenseByType(i, expenseList.get(2)));
            list.add("Medicine : " + dbAdapter.getExpenseByType(i, expenseList.get(3)));
            list.add("Entertainment" + dbAdapter.getExpenseByType(i, expenseList.get(4)));

            mBillItem.put(mListMonth.get(i - 1), list);
        }


       /* ArrayList<String> february = new ArrayList<>();
        february.add("Food : " + dbAdapter.getExpenseByType(2, expenseList.get(0)));
        february.add("Travel :" + dbAdapter.getExpenseByType(2, expenseList.get(1)));
        february.add("Petrol : " + dbAdapter.getExpenseByType(2, expenseList.get(2)));
        february.add("Medicine : " + dbAdapter.getExpenseByType(2, expenseList.get(3)));
        february.add("Entertainment" + dbAdapter.getExpenseByType(2, expenseList.get(4)));

        ArrayList<String> march = new ArrayList<>();
        march.add("Food : " + dbAdapter.getExpenseByType(3, expenseList.get(0)));
        march.add("Travel :" + dbAdapter.getExpenseByType(3, expenseList.get(1)));
        march.add("Petrol : " + dbAdapter.getExpenseByType(3, expenseList.get(2)));
        march.add("Medicine : " + dbAdapter.getExpenseByType(3, expenseList.get(3)));
        march.add("Entertainment" + dbAdapter.getExpenseByType(3, expenseList.get(4)));

        ArrayList<String> april = new ArrayList<>();
        april.add("Food : " + dbAdapter.getExpenseByType(4, expenseList.get(0)));
        april.add("Travel :" + dbAdapter.getExpenseByType(4, expenseList.get(1)));
        april.add("Petrol : " + dbAdapter.getExpenseByType(4, expenseList.get(2)));
        april.add("Medicine : " + dbAdapter.getExpenseByType(4, expenseList.get(3)));
        april.add("Entertainment" + dbAdapter.getExpenseByType(4, expenseList.get(4)));

        ArrayList<String> may = new ArrayList<>();
        may.add("Food : " + dbAdapter.getExpenseByType(5, expenseList.get(0)));
        may.add("Travel :" + dbAdapter.getExpenseByType(5, expenseList.get(1)));
        may.add("Petrol : " + dbAdapter.getExpenseByType(5, expenseList.get(2)));
        may.add("Medicine : " + dbAdapter.getExpenseByType(5, expenseList.get(3)));
        may.add("Entertainment" + dbAdapter.getExpenseByType(5, expenseList.get(4)));

        ArrayList<String> june = new ArrayList<>();
        june.add("Food : " + dbAdapter.getExpenseByType(6, expenseList.get(0)));
        june.add("Travel :" + dbAdapter.getExpenseByType(6, expenseList.get(1)));
        june.add("Petrol : " + dbAdapter.getExpenseByType(6, expenseList.get(2)));
        june.add("Medicine : " + dbAdapter.getExpenseByType(6, expenseList.get(3)));
        june.add("Entertainment" + dbAdapter.getExpenseByType(6, expenseList.get(4)));

        ArrayList<String> july = new ArrayList<>();
        july.add("Food : " + dbAdapter.getExpenseByType(7, expenseList.get(0)));
        july.add("Travel :" + dbAdapter.getExpenseByType(7, expenseList.get(1)));
        july.add("Petrol : " + dbAdapter.getExpenseByType(7, expenseList.get(2)));
        july.add("Medicine : " + dbAdapter.getExpenseByType(7, expenseList.get(3)));
        july.add("Entertainment" + dbAdapter.getExpenseByType(7, expenseList.get(4)));

        mBillItem.put(mListMonth.get(0), january); // Header, Child data
        mBillItem.put(mListMonth.get(1), february);
        mBillItem.put(mListMonth.get(2), march);
        mBillItem.put(mListMonth.get(3), may);*/


    }
}