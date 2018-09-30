package com.gnagpal.mycontactsapp.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gnagpal.mycontactsapp.Model.Message;

import java.util.List;

/**
 * Message Data Access Object. Gets messages from database,
 * persists changes back to database
 */
@Dao
public interface MessageDao {

    @Insert
    void insert(Message message);

    @Insert
    void bulkInsert(Message... messages);

    @Update
    void update(Message... messages);

    @Delete
    void delete(Message... messages);

    @Query("SELECT * FROM message ORDER BY sent_at DESC")
    List<Message> getAllMessages();
}
