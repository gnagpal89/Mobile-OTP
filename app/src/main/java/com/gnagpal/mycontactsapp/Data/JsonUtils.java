package com.gnagpal.mycontactsapp.Data;

import com.gnagpal.mycontactsapp.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static List<User> userList;

    /**
     * Converts list of User objects into a Json String
     * @return
     */
    public static String toJsonString(){
        userList = FakeUserData.loadUserList();
        JSONObject contactsJson = new JSONObject();

        JSONArray userArray = new JSONArray();
        try {
            for (User user : userList) {
                JSONObject userObject = new JSONObject();
                userObject.put("first_name", user.getFirstName());
                userObject.put("last_name", user.getLastName());
                userObject.put("phone", user.getPhone());
                userArray.put(userObject);
            }

             contactsJson.put("contacts", userArray);
        } catch (JSONException e){
            e.printStackTrace();
        }
        return userArray.toString();
    }

    /**
     * Gets list of user contacts from json string
     * @return
     */
    public static List<User> getUserListFromJson(){
        userList = new ArrayList<User>();
        String jsonString = toJsonString();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String firstName = jsonObject.getString("first_name");
                String lastName = jsonObject.getString("last_name");
                String phone = jsonObject.getString("phone");
                userList.add(new User(firstName, lastName, phone));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
