package com.gnagpal.mycontactsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnagpal.mycontactsapp.Data.AppDatabase;
import com.gnagpal.mycontactsapp.Model.Message;

import java.util.List;

/**
 * Portion of the UI showing the list of Messages sent.
 */
public class MessagesFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private MessagesAdapter mMessagesAdapter;

    AppDatabase mDatabase;

    public MessagesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);

        mRecyclerView = rootView.findViewById(R.id.messages);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mMessagesAdapter = new MessagesAdapter();

        mRecyclerView.setAdapter(mMessagesAdapter);

        mDatabase = AppDatabase.getInstance(getActivity().getApplicationContext());

        return rootView;
    }

    /* Loads messages from the database */
    private void loadMessagesData() {
        List<Message> messagesData;

        messagesData = mDatabase.messageDao().getAllMessages();

        mMessagesAdapter.setMessageData(messagesData);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadMessagesData();
    }
}
