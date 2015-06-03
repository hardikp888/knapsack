package com.example.hardik.knapsack.UI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Stranger on 1/8/2015.
 */
public class ViewExpenseFragment extends Fragment {

    private static final String TAG = "ViewExpenseFragment";
    private final View.OnClickListener mAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) getActivity()).changeTab(1);
        }
    };
    private RecyclerView mRecyclersViewExpense;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        mRecyclersViewExpense = (RecyclerView) view.findViewById(R.id.recycler_expense);
        ImageButton btnAdd = (ImageButton) view.findViewById(R.id.imgBtn_add);
        ViewCompat.setElevation(btnAdd, 20);
        btnAdd.setOnClickListener(mAddClickListener);

        final Calendar calendar = Calendar.getInstance(Locale.getDefault());

        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(getActivity());
        ArrayList<Expense> list = dataBaseAdapter.getMonthExpense((calendar.get(calendar.MONTH) + 1), calendar.get(calendar.YEAR));
        ExpenseAdapter adapter = new ExpenseAdapter(getActivity(), list);
        mRecyclersViewExpense.setAdapter(adapter);
        mRecyclersViewExpense.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
