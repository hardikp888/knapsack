package com.example.hardik.knapsack.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hardik.knapsack.BL.Expense;
import com.example.hardik.knapsack.DataBase.DataBaseAdapter;
import com.example.hardik.knapsack.R;

import java.util.ArrayList;

/**
 * Created by Stranger on 1/8/2015.
 */
public class BillFragment extends Fragment {

    RecyclerView mRecyclerViewBill;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        mRecyclerViewBill = (RecyclerView) view.findViewById(R.id.recycler_bill);

        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(getActivity());
        ArrayList<Expense> billList = dataBaseAdapter.getAllExpense();

        BillAdapter adapter = new BillAdapter(billList);

        mRecyclerViewBill.setAdapter(adapter);
        mRecyclerViewBill.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }
}
