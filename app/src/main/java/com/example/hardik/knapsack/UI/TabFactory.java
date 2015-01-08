package com.example.hardik.knapsack.UI;

import android.content.Context;
import android.view.View;
import android.widget.TabHost;

/**
 * Created by Stranger on 1/8/2015.
 */
public class TabFactory implements TabHost.TabContentFactory {

    private Context mContext;

    public TabFactory(Context context) {
        mContext = context;
    }

    @Override
    public View createTabContent(String tag) {
        final View view = new View(mContext);
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
    }
}
