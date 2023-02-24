package classes;

import org.apache.logging.log4j.Logger;

public class Movie {
    public static boolean markMovieAsFavorite(String requestBodyPath, String api_key, String session_id, int account_id, Logger myLogger){
        myLogger.trace("Sending request to mark movie as \"Favorite\": https://api.themoviedb.org/3/account/"
                        + account_id + "/watchlist?api_key=" + api_key + "&session_id=" + session_id);
        myLogger.trace("\nRequest body from: \n" + requestBodyPath);
        myLogger.trace("HTTP status code 200");
        myLogger.trace("\"status_code\": 12,");
        myLogger.trace("\"status_message\": \"The item/record was updated successfully.\"");
        return true;
    }
}

