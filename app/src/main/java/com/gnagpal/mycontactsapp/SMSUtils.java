package com.gnagpal.mycontactsapp;

import android.os.AsyncTask;
import android.util.Log;

import com.gnagpal.mycontactsapp.Model.Message;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Utility class using Kaleyra SMS Api Service to send the message
 */
public class SMSUtils {

    public static final String LOG_TAG = SMSUtils.class.getSimpleName();

    //Api key. Also mentioned in gradle build file.
    public static String SMS_API_KEY = "Ad55fa2f79a2bae452b4e6c7ed389088a";

    /* boolean to check if message is sent or not */
    static boolean isSuccess;

    public static boolean sendKaleyraSMS(Message message, String phone){
        String text = message.getMessageText();

        /* Frame the URL passing in the API key, text and the phone number */
        final String url = "https://api-alerts.kaleyra.com/v4/?api_key="
                    + URLEncoder.encode(SMS_API_KEY)
                    +"&method=sms&message="
                    +URLEncoder.encode(text)
                    +"&to="+phone+"&sender=INFINI";

        /* Sending the message in the background using AsyncTask */
        new AsyncTask<Void, Void, Void>() {

            Response response;
            @Override
            protected Void doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                try {
                    response = client.newCall(request).execute();
                    Log.i(LOG_TAG, "Response: "+response.toString());
                    if(response.code() != 200){
                        isSuccess = false;
                    }
                    isSuccess = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        return isSuccess;
    }
}
