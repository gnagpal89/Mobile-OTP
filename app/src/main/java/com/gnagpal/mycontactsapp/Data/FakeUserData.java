package com.gnagpal.mycontactsapp.Data;

import com.gnagpal.mycontactsapp.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class providing fake contacts
 */
public class FakeUserData {

    public static List<User> userList;

    public static List<User> loadUserList(){
        userList = new ArrayList<>();
        userList.add(new User("Gaurav", "Nagpal", "9910055606"));
        userList.add(new User("Munish", "Kumar", "9999954851"));
        userList.add(new User("Arpit", "Gandhi", "9910034606"));
        userList.add(new User("Gau3av", "Nagpal", "9910055606"));
        userList.add(new User("Ga1rav", "Nagpal", "9910055606"));
        userList.add(new User("G1urav", "Nagpal", "9910055606"));
        userList.add(new User("Gau5av", "Nagpal", "9910055606"));
        userList.add(new User("faurav", "Nagpal", "9910055606"));
        userList.add(new User("haurav", "Nagpal", "9910055606"));
        userList.add(new User("John", "Doe", "9971792703"));

        return userList;
    }
}
