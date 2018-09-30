package com.gnagpal.mycontactsapp.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gnagpal.mycontactsapp.Model.User;

import java.util.List;

/**
 * User Data Access Object. Gets contacts from database,
 * persists changes back to database
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> loadAllUsers();

    @Insert
    void insert(User user);

    @Insert
    void bulkInsert(List<User> users);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void clearAll();
}
