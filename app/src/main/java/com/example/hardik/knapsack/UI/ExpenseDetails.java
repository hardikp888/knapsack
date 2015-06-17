package com.example.hardik.knapsack.UI;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hardik.knapsack.BL.Expense;
import com.example.hardik.knapsack.BL.Global;
import com.example.hardik.knapsack.DataBase.DataBaseAdapter;
import com.example.hardik.knapsack.R;

public class ExpenseDetails extends ActionBarActivity {

    Expense mExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        final int expenseId = getIntent().getIntExtra(Global.EXPENSE_ID, 0);
        mExpense = (new DataBaseAdapter(ExpenseDetails.this)).getExpenseByID(expenseId);

        final TextView txtType = (TextView) findViewById(R.id.txt_exp_type_value);
        final TextView txtAmount = (TextView) findViewById(R.id.txt_exp_amount_value);
        final TextView txtDescription = (TextView) findViewById(R.id.txt_exp_description_value);
        final TextView txtDate = (TextView) findViewById(R.id.txt_exp_date_value);
        final ImageView imageView = (ImageView) findViewById(R.id.img_exp_photo);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);


        txtType.setText(mExpense.getType());
        txtAmount.setText(String.valueOf(mExpense.getAmount()));
        txtDescription.setText(mExpense.getDescription());
        txtDate.setText(mExpense.getDate());

        /*if (mExpense.getPhoto() != null && (!mExpense.getPhoto().isEmpty())) {
            imageView.setImageURI(Uri.fromFile(new File(mExpense.getPhoto())));
        }*/

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
