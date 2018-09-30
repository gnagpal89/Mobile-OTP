package com.gnagpal.mycontactsapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * A simple pager adapter that represents 2 MyPagerAdapter objects, in
 * sequence.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /* Instantiates the return the ContactsFragment or MessagesFragment */
    @Override
    public Fragment getItem(int position) {
        Fragment contactsFragment = new ContactsFragment();
        Fragment messagesFragment = new MessagesFragment();
        switch(position){
            case 0: return contactsFragment;
            case 1: return messagesFragment;
        }
        return null;
    }

    /* Returns the number of pages */
    @Override
    public int getCount() {
        return 2;
    }

    /* Returns the tab titles for each page */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Contacts";
            case 1: return "Messages";
            default: return null;
        }
    }
}
