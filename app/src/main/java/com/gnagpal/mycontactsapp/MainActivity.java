package com.gnagpal.mycontactsapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gnagpal.mycontactsapp.Data.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /*
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mViewPager;

    /*
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private MyPagerAdapter myPagerAdapter;

    /*
     * TabLayout provides a horizontal layout to display tabs.
     */
    private TabLayout mTabLayout;

    AppDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.pager);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myPagerAdapter);

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);

        /*
         * Get database instance
         */
        mdb = AppDatabase.getInstance(getApplicationContext());
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        /**
//         * Clear All Contacts from Database
//         */
//        mdb.userDao().clearAll();
//    }
}
