package com.gnagpal.mycontactsapp;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gnagpal.mycontactsapp.Model.User;

/**
 * In this Activity, Details of the selected contact is shown
 */
public class DetailActivity extends AppCompatActivity {
    /* Contact whose details are shown */
    private User userContact;

    /* Textview showing user name */
    private TextView mTextViewName;

    /* TextView showing phone number of the user */
    private TextView mTextViewPhone;

    /* Button to send the message */
    private Button mButtonSendOTP;

    /**
     * A dialog fragment{@link SendOTPDialogFragment} showing message text.
     */
    private SendOTPDialogFragment sendOTPDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTextViewName = findViewById(R.id.tv_contact_name);
        mTextViewPhone = findViewById(R.id.tv_contact_phone);

        /** Get the user contact from the intent passed by {@link ContactsFragment}*/
        userContact = (User) getIntent().getSerializableExtra("contact");

        /* pass the name and the phone number taken from the user to their views */
        mTextViewName.setText(userContact.getFirstName()+" "+ userContact.getLastName());
        mTextViewPhone.setText(userContact.getPhone());

        /* Store the user contact to a bundle to be passed to the dialog showing the message*/
        final Bundle args = new Bundle();
        args.putSerializable("contact", userContact);

        /* Interface for interacting with Fragment objects inside of an Activity */
        final FragmentManager fm = getFragmentManager();

        mButtonSendOTP = findViewById(R.id.btn_send_otp);

        /**
         * OnClickListener attached to the button which passes the contact details
         * and opens the Message Dialog Fragment
         */
        mButtonSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTPDialogFragment = new SendOTPDialogFragment();
                sendOTPDialogFragment.setArguments(args);
                sendOTPDialogFragment.show(fm, "dialog");
            }
        });
    }
}
