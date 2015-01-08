package com.example.hardik.knapsack.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hardik.knapsack.R;

/**
 * Created by Stranger on 1/8/2015.
 */
public class ViewExpenseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);

        return view;
    }
}
