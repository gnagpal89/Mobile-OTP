package com.gnagpal.mycontactsapp.Model;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Helps to save date in SQLite Database
 */
public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null ? null : date.getTime();
    }
}
