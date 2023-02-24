package classes;

import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Account {
    private Object avatar;
    private int id;
    private  String iso_639_1;
    private String iso_3166_1;
    private String name;
    private boolean include_adult;
    private String username;

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public static int requestAccountDetails(Session currentSession, User currentUser, ArrayList<Account> accounts, Logger myLogger ){
        myLogger.trace("Sending request for account details: https://api.themoviedb.org/3/account?api_key=" +
                currentUser.getApi_key()+ "&session_id="+currentSession.getSession_id());
        myLogger.trace("HTTP status code 200");
        int accountIdx = -1;
        for(int i = 0; i<accounts.size(); i++){
            if(accounts.get(i).getUsername().equals(currentUser.getUsername())){
                accountIdx = i;
            }
        }
        return accountIdx;
    }
}
