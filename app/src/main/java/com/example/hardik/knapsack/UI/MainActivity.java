package com.example.hardik.knapsack.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.example.hardik.knapsack.R;

import java.util.List;
import java.util.Vector;


public class MainActivity extends ActionBarActivity {

    private TabHost mTabHost;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList = new Vector<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Utils.storeSIMData(MainActivity.this);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        addTab();
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);


        mTabHost.setOnTabChangedListener(mTabChangeListener);
        mViewPager.setOnPageChangeListener(mPageChangeListener);
    }

    private void addTab() {

        TabHost.TabSpec tabSpec1 = mTabHost.newTabSpec("Insert");
        tabSpec1.setIndicator("Insert");
        tabSpec1.setContent(new TabFactory(MainActivity.this));
        mTabHost.addTab(tabSpec1);
        mFragmentList.add(Fragment.instantiate(this, InsertExpenseFragment.class.getName()));

        TabHost.TabSpec tabSpec2 = mTabHost.newTabSpec("View");
        tabSpec2.setIndicator("View");
        tabSpec2.setContent(new TabFactory(MainActivity.this));
        mTabHost.addTab(tabSpec2);
        mFragmentList.add(Fragment.instantiate(this, ViewExpenseFragment.class.getName()));

        TabHost.TabSpec tabSpec3 = mTabHost.newTabSpec("Bill");
        tabSpec3.setIndicator("Bill");
        tabSpec3.setContent(new TabFactory(MainActivity.this));
        mTabHost.addTab(tabSpec3);
        mFragmentList.add(Fragment.instantiate(this, BillFragment.class.getName()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            TabWidget tabWidget = mTabHost.getTabWidget();
            int oldFocus = tabWidget.getDescendantFocusability();
            tabWidget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            mTabHost.setCurrentTab(position);
            tabWidget.setDescendantFocusability(oldFocus);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private TabHost.OnTabChangeListener mTabChangeListener = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabId) {
            int position = mTabHost.getCurrentTab();
            mViewPager.setCurrentItem(position);
        }
    };
}
