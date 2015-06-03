package com.example.hardik.knapsack.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hardik.knapsack.BL.Expense;
import com.example.hardik.knapsack.BL.Global;
import com.example.hardik.knapsack.R;

import java.util.ArrayList;

/**
 * Created by Stranger on 1/11/2015.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {

    private static final String TAG = "ExpenseAdapter";
    private ArrayList<Expense> mExpenseList;
    private Context mContext;
    View.OnClickListener mExpenseClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = ((RecyclerView) v.getParent()).getChildPosition(v);
            Log.i(TAG, "Select Expense ID : " + mExpenseList.get(pos).getId());

            ((Activity) mContext).finish();
            Intent intent = new Intent(mContext, ExpenseDetails.class);
            intent.putExtra(Global.EXPENSE_ID, mExpenseList.get(pos).getId());
            mContext.startActivity(intent);
        }
    };


    public ExpenseAdapter(Context context, ArrayList<Expense> expenseList) {
        mExpenseList = expenseList;
        mContext = context;
    }

    @Override
    public ExpenseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_raw, parent, false);
        view.setOnClickListener(mExpenseClick);
        return new ExpenseHolder(view);
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
