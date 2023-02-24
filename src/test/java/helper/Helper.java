package helper;

import classes.Account;
import classes.Network;
import classes.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Helper {
    public static ArrayList<User>  jsonUsersToArrayList(String jsonPath){
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
        try {
            return gson.fromJson(new FileReader(jsonPath), userListType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Network>  jsonNetworkToArrayList(String jsonPath){
        Gson gson = new Gson();
        Type networkListType = new TypeToken<ArrayList<Network>>(){}.getType();
        try {
            return gson.fromJson(new FileReader(jsonPath), networkListType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Account>  jsonAccountToArrayList(String jsonPath){
        Gson gson = new Gson();
        Type accountListType = new TypeToken<ArrayList<Account>>(){}.getType();
        try {
            return gson.fromJson(new FileReader(jsonPath), accountListType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
