package classes;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;


public class Session {
    private static Session singleSession = null;

    private String session_id;
    private String request_token;

    private Session(){}
    public static Session getInstance()
    {
        if (singleSession == null)
            singleSession = new Session();
        return singleSession;
    }
    public String getSession_id() {
        return session_id;
    }
    public void requestToken_Happy(User currentUser, Logger myLogger){
        String api_key = currentUser.getApi_key();
        this.request_token = RandomStringUtils.randomAlphanumeric(40);
        myLogger.trace("Connecting to API...");
        myLogger.trace("API key for user: " + currentUser.getUsername() + " obtained from .json");
        myLogger.trace("Sending request for token: " + "https://api.themoviedb.org/3/authentication/token/new?api_key="
                        + api_key);
        myLogger.trace("HTTP status code 200");
        myLogger.trace("\"success\": true,");
        myLogger.trace("\"request_token\":  \"" + request_token + "\"");
    }
    public boolean requestToken_Sad(User currentUser, Logger myLogger){
        String api_key = currentUser.getApi_key() + RandomStringUtils.randomAlphanumeric(5);
        myLogger.trace("Connecting to API...");
        myLogger.trace("API key for user: " + currentUser.getUsername() + " obtained from .json");
        myLogger.trace("Sending request for token: " + "https://api.themoviedb.org/3/authentication/token/new?api_key="
                + api_key);
        myLogger.error("HTTP status code 401");
        myLogger.info("\"success\": false,");
        myLogger.info("\"status_message\": \"Invalid API key: You must be granted a valid key.\"");
        return false;
    }
    public void validateRequestToken(User currentUser, Logger myLogger){
        String api_key = currentUser.getApi_key();
        myLogger.trace("Sending request for validation of token: " + "https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key="
                + api_key);
        myLogger.trace("\nRequest body: \n"
                + "\"username\": " + currentUser.getUsername() + "\n"
                + "\"password\": " + currentUser.getPassword() + "\n"
                + "\"request_token\": " + request_token + "\""
        );
        myLogger.trace("HTTP status code 200");
        myLogger.trace("\"success\": true,");
    }
    public boolean createSessionID(User currentUser, Logger myLogger){
        String api_key = currentUser.getApi_key();
        this.session_id = RandomStringUtils.randomAlphanumeric(40);
        myLogger.trace("Sending request to create a session id: " + "https://api.themoviedb.org/3/authentication/session/new?api_key="
                + api_key);
        myLogger.trace("\nRequest body: \n"
                + "\"request_token\": \"" + request_token);
        myLogger.trace("HTTP status code 200");
        myLogger.trace("\"success\": true,");
        myLogger.trace("\"session_id\":  \"" + session_id + "\"");
        return true;
    }
}
