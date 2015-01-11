package com.example.hardik.knapsack.UI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hardik.knapsack.BL.Expense;
import com.example.hardik.knapsack.R;

import java.util.ArrayList;

/**
 * Created by Stranger on 1/11/2015.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {

    private ArrayList<Expense> mExpenseList;

    public ExpenseAdapter(ArrayList<Expense> expenseList) {
        mExpenseList = expenseList;
    }


    @Override
    public ExpenseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_raw, parent, false);
        return new ExpenseHolder(view);
    }

    public void updateList(ArrayList<Expense> expenseList) {
        mExpenseList.clear();
        mExpenseList.addAll(expenseList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ExpenseHolder holder, int position) {
        Expense expense = mExpenseList.get(position);

        holder.mTxtType.setText(expense.getType());
        holder.mTxtAmount.setText(String.valueOf(expense.getAmount()));
        holder.mTxtDate.setText(expense.getDate());
    }

    @Override
    public int getItemCount() {
        return mExpenseList.size();
    }

    public static class ExpenseHolder extends RecyclerView.ViewHolder {

        final TextView mTxtType;
        final TextView mTxtAmount;
        final TextView mTxtDate;

        public ExpenseHolder(View itemView) {
            super(itemView);
            mTxtType = (TextView) itemView.findViewById(R.id.txt_exp_type);
            mTxtAmount = (TextView) itemView.findViewById(R.id.txt_exp_amount);
            mTxtDate = (TextView) itemView.findViewById(R.id.txt_exp_date);
        }
    }
}
