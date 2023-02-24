package classes;

import org.apache.logging.log4j.Logger;

public class Network {
     private String headquarters;
     private String homepage;
     private int id;
     private String logo_path;
     private String name;
     private  String origin_country;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String searchNetworkById(String api_key, Network currentNetwork, Logger myLogger){
        myLogger.trace("User is going to search network \""
                        + currentNetwork.getName()
                        + "\" with ID \"" +currentNetwork.getId()
                        + "\"");
        myLogger.trace("Connecting to API...");
        myLogger.trace("Sending request for network details: " + "https://api.themoviedb.org/3/network/"
                + currentNetwork.getId() + "?api_key=" + api_key);
        myLogger.trace("HTTP status code 200");
        myLogger.trace("\"success\": true,");
        myLogger.trace("\"name\": \"" + currentNetwork.getName() + "\"");
        return currentNetwork.getName();
    }
}
