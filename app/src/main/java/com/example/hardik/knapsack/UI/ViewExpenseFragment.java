package com.example.hardik.knapsack.UI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private RecyclerView mRecyclersViewExpense;
    private static final String TAG = "ViewExpenseFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"----------onCreate");
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
        ExpenseAdapter adapter = new ExpenseAdapter(list);
        mRecyclersViewExpense.setAdapter(adapter);
        mRecyclersViewExpense.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(TAG, "----------onCreateView");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(getActivity());
        ArrayList<Expense> list = dataBaseAdapter.getMonthExpense((calendar.get(calendar.MONTH) + 1), calendar.get(calendar.YEAR));
        ((ExpenseAdapter) mRecyclersViewExpense.getAdapter()).updateList(list);
        Log.d(TAG, "----------onResume");*/
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"----------onStart");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "----------onActivityCreated");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "----------on Attach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"----------onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG,"----------onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"----------onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "----------on Detach");
    }

    private final View.OnClickListener mAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) getActivity()).changeTab(1);
        }
    };
}
