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

    }
}