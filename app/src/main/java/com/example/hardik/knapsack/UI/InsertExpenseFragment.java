package com.example.hardik.knapsack.UI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hardik.knapsack.BL.Expense;
import com.example.hardik.knapsack.BL.Global;
import com.example.hardik.knapsack.DataBase.DataBaseAdapter;
import com.example.hardik.knapsack.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class InsertExpenseFragment extends Fragment {

    private final static String TAG = "InsertExpenseFragment";
    private static final int CAPTURE_PHOTO = 100;
    DatePickerDialog.OnDateSetListener mDateChangedListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        }
    };
    private Dialog mDialogList;
    private final View.OnFocusChangeListener mExpTypeFocusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                showListDialog();
            }
        }
    };
    private final View.OnClickListener mExpTypeOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showListDialog();
        }
    };
    private String mPhotoFile = "";
    private Uri mFileUri = null;
    private EditText mEdTxtType;
    private final AdapterView.OnItemClickListener mExpListItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String expenseType = (String) parent.getItemAtPosition(position);
            if (expenseType != null && expenseType.length() > 0) {
                mEdTxtType.setText(expenseType);
            }

            if (mDialogList != null) {
                mDialogList.dismiss();
                mDialogList.cancel();
            }
        }
    };
    private EditText mEdTxtAmount;
    private EditText mEdTxtDes;
    private EditText mEdTxtDate;
    private final View.OnFocusChangeListener mDateFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                showDateDialog();
            }
        }
    };
    private final View.OnClickListener mBtnInsertClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (validateData()) {
                Expense expense = new Expense();
                expense.setType(mEdTxtType.getText().toString().trim());
                expense.setDate(mEdTxtDate.getText().toString().trim());
                expense.setDescription(mEdTxtDes.getText().toString().trim());
                expense.setAmount(Integer.parseInt(mEdTxtAmount.getText().toString().trim()));
                if (!mPhotoFile.isEmpty() && mPhotoFile != null) {
                    expense.setPhoto(mPhotoFile);
                } else {
                    expense.setPhoto("N/A");
                }

                DataBaseAdapter dataBaseAdapter = new DataBaseAdapter(getActivity());
                dataBaseAdapter.insetExpense(expense);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getActivity().finish();
                startActivity(intent);
            } else {
                Log.d(TAG, "Invalid Data");
            }
        }
    };
    private ImageView mImgPhoto;
    private CheckBox mChkPhoto;
    private final View.OnClickListener mCaptureClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (mChkPhoto.isChecked()) {
                mFileUri = getFileUri();
                final Intent cameraIntent = new Intent();
                cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
                startActivityForResult(cameraIntent, CAPTURE_PHOTO);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);
        mEdTxtType = (EditText) view.findViewById(R.id.edtxt_type);
        mEdTxtAmount = (EditText) view.findViewById(R.id.edtxt_amount);
        mEdTxtDes = (EditText) view.findViewById(R.id.edtxt_description);
        mEdTxtDate = (EditText) view.findViewById(R.id.edtxt_date);
        mImgPhoto = (ImageView) view.findViewById(R.id.img_photo);

        Button btnInsert = (Button) view.findViewById(R.id.btn_insert_expense);
        btnInsert.setOnClickListener(mBtnInsertClick);

        mChkPhoto = (CheckBox) view.findViewById(R.id.chk_attachPhoto);
        mChkPhoto.setOnClickListener(mCaptureClickListener);

        mEdTxtDate.setOnFocusChangeListener(mDateFocusChange);
        mEdTxtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        mEdTxtType.setOnFocusChangeListener(mExpTypeFocusListener);
        mEdTxtType.setOnClickListener(mExpTypeOnclick);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {

                mImgPhoto.setVisibility(View.VISIBLE);

                try {
                    final Bitmap bitmap = BitmapFactory.decodeFile(mFileUri
                            .getPath());

                    mImgPhoto.setImageBitmap(bitmap);

                } catch (final Exception e) {
                    Log.d("MainActitivty", "Error in set Image ");
                }
            }
            mImgPhoto.setContentDescription(mPhotoFile);
        }
    }

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

    private void showListDialog() {

        mDialogList = new Dialog(getActivity());
        mDialogList.setTitle(getString(R.string.expense_type));
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_list, null, false);
        mDialogList.setContentView(view);

        ListView listView = (ListView) view.findViewById(R.id.list_dialog);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, Global.getExpenseType());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mExpListItemClick);

        mDialogList.show();
    }

    private Uri getFileUri() {

        final File photoFileDir = new File(
                Environment.getExternalStorageDirectory() + "/Expense");

        if (!photoFileDir.exists()) {
            if (!photoFileDir.mkdir()) {
                Toast.makeText(getActivity(), "Error in cerate directory",
                        Toast.LENGTH_LONG).show();
            }
        }

        final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());

        final File photoFile = new File(photoFileDir.getPath() + File.separator
                + "IMG_EXP_" + timeStamp + ".jpg");
        mPhotoFile = photoFile.getAbsolutePath();

        return Uri.fromFile(photoFile);
    }

    private boolean validateData() {

        if (mEdTxtType.getText().toString().length() <= 0) {
            mEdTxtType.setError(getString(R.string.expense_type));
            return false;
        } else {
            mEdTxtType.setError(null);
        }

        if (mEdTxtDate.getText().toString().length() <= 0) {
            mEdTxtDate.setError(getString(R.string.expense_date));
            return false;
        } else {
            mEdTxtDate.setError(null);
        }

        if (mEdTxtDes.getText().toString().length() <= 0) {
            mEdTxtDes.setError(getString(R.string.expense_des));
            return false;
        } else {
            mEdTxtDes.setError(null);
        }

        if (mEdTxtAmount.getText().toString().length() <= 0) {
            mEdTxtAmount.setError(getString(R.string.expense_amount));
            return false;
        } else {
            mEdTxtAmount.setError(null);
        }

        /*if (mImgPhoto.getContentDescription().toString().length() <= 0) {
            mImgPhoto.setContentDescription("N/A");
        }*/


        return true;
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri", mFileUri);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mFileUri = savedInstanceState.getParcelable("file_uri");
    }*/
}
