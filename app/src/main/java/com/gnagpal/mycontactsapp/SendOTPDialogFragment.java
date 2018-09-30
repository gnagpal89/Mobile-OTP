package com.gnagpal.mycontactsapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.gnagpal.mycontactsapp.Data.AppDatabase;
import com.gnagpal.mycontactsapp.Model.Message;
import com.gnagpal.mycontactsapp.Model.User;

import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A DialogFragment window that frames the message to be sent.
 */
public class SendOTPDialogFragment extends DialogFragment {

    AppDatabase mDatabase;

    /* Message to be saved on sending the message */
    Message message;

    /**
     * Creates the Dialog window
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDatabase = AppDatabase.getInstance(getActivity());

        /* User contact object passed by the DetailActivity */
        User contact = (User) getArguments().getSerializable("contact");

        String firstName = contact.getFirstName();
        String lastName = contact.getLastName();
        String userName = firstName+" "+lastName;
        final String phone = contact.getPhone();

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /* Text message to be sent */
        String otpText = getString(R.string.otp_message, generateOTP());

        /* Get present Date */
        Date date = new Date();
        
        /* Construct the Message object*/
        message = new Message(otpText, userName, date);

        /* Constructing Dialog only if phone number is valid else showing a Toast message */
        if(isValidPhoneNumber(phone)) {
            builder.setMessage(otpText)
                    .setPositiveButton(R.string.send_otp, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Sending message
                            if(SMSUtils.sendKaleyraSMS(message, phone)){
                                Toast.makeText(getActivity(), "OTP sent", Toast.LENGTH_SHORT).show();
                                //Saving the message in the database
                                storeMessage(message);
                            } else {
                                Toast.makeText(getActivity(), "OTP sending failed. Try Again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton(R.string.cancel_send, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            return builder.create();
        } else {
            Toast.makeText(getActivity(), "Invalid number", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * Saving the message in the database
     * @param message
     */
    private void storeMessage(Message message) {
        mDatabase.messageDao().insert(message);
    }

    /* Generate a random 6 digit OTP */
    public static int generateOTP(){
        Random rnd = new Random();
        return 100000 + rnd.nextInt(900000);
        //return Integer.toString(no);
    }

    /**
     * Check if phone number is valid or not
     * @param phone
     * @return
     */
    public static boolean isValidPhoneNumber(String phone){
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");

        Matcher matcher = p.matcher(phone);
        return (matcher.find() && matcher.group().equals(phone));
    }
}
