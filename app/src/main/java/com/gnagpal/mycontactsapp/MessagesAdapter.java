package com.gnagpal.mycontactsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnagpal.mycontactsapp.Model.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Passes the list of messages to a Recycler view
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {

    private List<Message> mMessageData;

    public MessagesAdapter(){

    }

    /* Pass the inflated view and create the MessagesViewHolder */
    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.message_list_item, parent, false);
        return new MessagesViewHolder(view);
    }

    /* Bind Message data to the each viewholder */
    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        Message message = mMessageData.get(position);

        String name = message.getUserName();
        String text= message.getMessageText();
        Date date = message.getSentAt();
        String dateString = convertDateToString(date);

        /* Pass the message data to each of their views */
        holder.mTextViewUserName.setText(name);
        holder.mTextViewDate.setText(dateString);
        holder.mTextViewMessageText.setText(text);
    }

    private String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM hh:mm");
        String strDate = formatter.format(date);
        return strDate;
    }

    /* returns the number of items to display */
    @Override
    public int getItemCount() {
        if(mMessageData == null){
            return 0;
        }
        return mMessageData.size();
    }

    /*
     * A ViewHolder is a required part of the pattern for RecyclerViews.
     * It has access to the adapter and the views.
     */
    public class MessagesViewHolder extends RecyclerView.ViewHolder{
        public final TextView mTextViewUserName;

        public final TextView mTextViewDate;

        public final TextView mTextViewMessageText;

        public MessagesViewHolder(View itemView) {
            super(itemView);
            mTextViewUserName = itemView.findViewById(R.id.tv_message_name);
            mTextViewDate = itemView.findViewById(R.id.tv_message_date);
            mTextViewMessageText = itemView.findViewById(R.id.tv_message_text);
        }
    }

    /* Passes the list of messages to MessagesAdapter */
    public void setMessageData(List<Message> messageData){
        mMessageData = new ArrayList<>(messageData);
        notifyDataSetChanged();
    }
}
