package com.gnagpal.mycontactsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnagpal.mycontactsapp.Model.User;

import java.util.List;

/**
 * {@link ContactsAdapter} exposes a list of user contacts
 * from an list to a {@link android.support.v7.widget.RecyclerView}.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsAdapterViewHolder> {
    private List<User> mContactData;

    /*
     * Below, we've defined an interface to handle clicks on items within this Adapter. In the
     * constructor of our ContactsAdapter, we receive an instance of a class that has implemented
     * this interface. We store that instance in this variable to call the onClick method whenever
     * an item is clicked in the list.
     */
    private ContactsAdapterClickHandler mClickHandler;

    /**
     * Creates a ContactsAdapter
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public ContactsAdapter(ContactsAdapterClickHandler clickHandler){
        mClickHandler = clickHandler;
    }

    /**
     * The interface that receives onClick messages.
     */
    public interface ContactsAdapterClickHandler{
        void onClick(int contactData);
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item, you
     *                  can use this viewType integer to provide a different layout.
     * @return A new ContactsAdapterViewHolder that holds the View for each list item
     */
    @NonNull
    @Override
    public ContactsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.contact_list_item, parent, false);
        return new ContactsAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the contact
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ContactsAdapterViewHolder holder, int position) {
        String contactData = mContactData.get(position).toString();
        holder.mContactTextView.setText(contactData);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our contacts
     */
    @Override
    public int getItemCount() {
        if(mContactData == null){
            return 0;
        }
        return mContactData.size();
    }
    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    public class ContactsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mContactTextView;

        public ContactsAdapterViewHolder(View itemView) {
            super(itemView);
            mContactTextView = itemView.findViewById(R.id.tv_contact_data);
            itemView.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click. We fetch the item that has been
         * selected, and then call the onClick handler registered with this adapter, passing that
         * item position.
         *
         * @param v the View that was clicked
         */
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //String contactSelected = mContactData[position];
            mClickHandler.onClick(position);
        }
    }

    /**
     * This gets called to pass list of contacts to the {@link ContactsAdapter}
     * @param contactData
     */
    public void setContactData(List<User> contactData){
        mContactData = contactData;
        notifyDataSetChanged();
    }
}
