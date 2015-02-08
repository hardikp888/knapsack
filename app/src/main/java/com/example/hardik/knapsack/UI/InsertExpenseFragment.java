package com.example.hardik.knapsack.UI;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.example.hardik.knapsack.BL.Expense;
import com.example.hardik.knapsack.DataBase.DataBaseAdapter;
import com.example.hardik.knapsack.R;

import java.util.Calendar;
import java.util.TimeZone;

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

        mEdTxtDate.setOnFocusChangeListener(mDateFocusChange);
        mEdTxtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

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

            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getActivity().finish();
            startActivity(intent);
        }
    };

    private final View.OnFocusChangeListener mDateFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                showDateDialog();
            }
        }
    };


    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog dialog = new DatePickerDialog(this.getActivity(),
                mDateChangedListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle(getString(R.string.select_date));

        final DatePicker datePicker = dialog.getDatePicker();

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mEdTxtDate.setText(datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear());
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.back), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    DatePickerDialog.OnDateSetListener mDateChangedListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        }
    };


    private void showListDialog() {

        Dialog dialog = new Dialog(getActivity());
        dialog.setTitle(getString(R.string.expense_type));
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_list, null, false);
        dialog.setContentView(view);

        ListView listView = (ListView) view.findViewById(R.id.list_dialog);
        Button btnBack = (Button) view.findViewById(R.id.btn_dialog_back);

        dialog.show();
    }
}
