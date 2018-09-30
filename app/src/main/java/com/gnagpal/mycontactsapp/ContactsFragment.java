package com.gnagpal.mycontactsapp;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnagpal.mycontactsapp.Data.AppDatabase;
import com.gnagpal.mycontactsapp.Data.JsonUtils;
import com.gnagpal.mycontactsapp.Model.User;

import java.util.List;

public class ContactsFragment extends Fragment implements ContactsAdapter.ContactsAdapterClickHandler {

    private RecyclerView mRecyclerView;

    private ContactsAdapter mContactsAdapter;

    private AppDatabase mDatabase;

    public ContactsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView.
         */
        mRecyclerView = rootView.findViewById(R.id.rv_contacts);
        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list.
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        /*
         * The ForecastAdapter is responsible for linking our contacts data with the Views that
         * will end up displaying our contacts data.
         */
        mContactsAdapter = new ContactsAdapter(this);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(mContactsAdapter);

        /* Get database instance */
        mDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());

        /* save fake contacts to database */
        saveContactsData();
        return rootView;
    }

    /**
     * This method is for responding to clicks from our list.
     * @param contactId Selected contact Id
     */
    @Override
    public void onClick(int contactId) {
        List<User> users = mDatabase.userDao().loadAllUsers();

        Intent detailActivityIntent = new Intent(getActivity(), DetailActivity.class);
        detailActivityIntent.putExtra("contact", users.get(contactId));
        startActivity(detailActivityIntent);
    }

    /* Method to save contacts to db */
    private void saveContactsData(){
        List<User> contactsData = JsonUtils.getUserListFromJson();
        if(contactsData!=null) {
            mDatabase.userDao().bulkInsert(contactsData);
        }
    }

    /**
     * Passes the data from database to the adapter
     */
    @Override
    public void onResume() {
        super.onResume();
        mContactsAdapter.setContactData(mDatabase.userDao().loadAllUsers());
    }
}
