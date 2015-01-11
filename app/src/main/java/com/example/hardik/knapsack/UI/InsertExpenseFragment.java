package com.example.hardik.knapsack.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hardik.knapsack.BL.Expense;
import com.example.hardik.knapsack.DataBase.DataBaseAdapter;
import com.example.hardik.knapsack.R;

/**
 * Created by Stranger on 1/8/2015.
 */
public class InsertExpenseFragment extends Fragment {

    private EditText mEdTxtType;
    private EditText mEdTxtAmount;
    private EditText mEdTxtDes;
    private EditText mEdTxtDate;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);
        mEdTxtType = (EditText) view.findViewById(R.id.edtxt_type);
        mEdTxtAmount = (EditText) view.findViewById(R.id.edtxt_amount);
        mEdTxtDes = (EditText) view.findViewById(R.id.edtxt_description);
        mEdTxtDate = (EditText) view.findViewById(R.id.edtxt_date);

        Button btnInsert = (Button) view.findViewById(R.id.btn_insert_expense);
        btnInsert.setOnClickListener(mInsertClick);

        return view;
    }


    private final View.OnClickListener mInsertClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Expense expense = new Expense();
            expense.setType(mEdTxtType.getText().toString().trim());
            expense.setDate(mEdTxtDate.getText().toString().trim());
            expense.setDescription(mEdTxtDes.getText().toString().trim());
            expense.setAmount(Integer.parseInt(mEdTxtAmount.getText().toString().trim()));

            DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(getActivity());
            dataBaseAdapter.insetExpense(expense);

            ((MainActivity) getActivity()).changeTab(0);
        }
    };
}
