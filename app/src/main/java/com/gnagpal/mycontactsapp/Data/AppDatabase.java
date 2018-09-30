package com.gnagpal.mycontactsapp.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.gnagpal.mycontactsapp.Model.DateConverter;
import com.gnagpal.mycontactsapp.Model.Message;
import com.gnagpal.mycontactsapp.Model.User;

/**
 * Database. Include the list of entities associated with the database within the annotation.
 */
@Database(entities = {User.class, Message.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase{

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "contacts";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if(sInstance == null) {
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new db instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting db instance");
        return sInstance;
    }

    public abstract UserDao userDao();
    public abstract MessageDao messageDao();
}
