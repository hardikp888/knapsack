package com.example.hardik.knapsack.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.hardik.knapsack.BL.Expense;
import com.example.hardik.knapsack.DataBase.DataBaseAdapter;
import com.example.hardik.knapsack.R;

import java.util.ArrayList;

/**
 * Created by Stranger on 1/8/2015.
 */
public class ViewExpenseFragment extends Fragment {

    private RecyclerView mRecyclersViewExpense;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        mRecyclersViewExpense = (RecyclerView) view.findViewById(R.id.recycler_expense);
        ImageButton btnAdd = (ImageButton) view.findViewById(R.id.imgBtn_add);
        ViewCompat.setElevation(btnAdd, 6);
        btnAdd.setOnClickListener(mAddClickListener);

        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(getActivity());
        ArrayList<Expense> list = dataBaseAdapter.getAllExpense();
        ExpenseAdapter adapter = new ExpenseAdapter(list);
        mRecyclersViewExpense.setAdapter(adapter);
        mRecyclersViewExpense.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


    private final View.OnClickListener mAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InsertExpenseFragment insertExpenseFragment = new InsertExpenseFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(android.R.id.tabcontent, insertExpenseFragment);
            fragmentTransaction.commit();

        }
    };
}
