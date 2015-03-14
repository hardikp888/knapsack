package com.example.hardik.knapsack.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.hardik.knapsack.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Stranger on 3/3/2015.
 */
public class BillAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mListMonth;
    private HashMap<String, ArrayList<String>> mBillList;

    BillAdapter(Context context, List<String> month, HashMap<String, ArrayList<String>> bill) {
        mContext = context;
        mListMonth = month;
        mBillList = bill;
    }

    @Override
    public int getGroupCount() {
        return mListMonth.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mBillList.get(mListMonth.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListMonth.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mBillList.get(mListMonth.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expanded_raw_group, null, false);
        }

        TextView txtGroupText = (TextView) convertView.findViewById(R.id.expand_raw_bill_month);
        txtGroupText.setText(groupTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expanded_raw_item, null, false);
        }

        TextView txtGroupText = (TextView) convertView.findViewById(R.id.expanded_raw_bill_item);
        txtGroupText.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
